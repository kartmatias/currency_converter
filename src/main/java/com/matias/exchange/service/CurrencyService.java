package com.matias.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matias.exchange.model.*;
import com.matias.exchange.repository.CurrencyRepository;
import com.matias.exchange.repository.LoginRepository;
import com.matias.exchange.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class CurrencyService {

    @Value("${exchangeratesapi.key}")
    private String accessKey;

//    EXTERNAL API
//    private final String UPDATE_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=%s&format=1";
//    MOCK API
    private final String UPDATE_URL = "http://localhost:3000/latest";

    private final CurrencyRepository currencyRepository;
    private final LoginRepository loginRepository;
    private final TransactionRepository transactionRepository;

    public CurrencyService(CurrencyRepository currencyRepository, ApplicationEventPublisher publisher, LoginRepository loginRepository, TransactionRepository transactionRepository) {
        this.currencyRepository = currencyRepository;
        this.loginRepository = loginRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Currency> all() {
        return StreamSupport
                .stream(currencyRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Currency getByName(String name) {
        return currencyRepository.findByName(name);
    }

    public void updateCurrency() throws IOException {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(UPDATE_URL, accessKey))
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            String jsonBody = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();

            ExchangeApi api = objectMapper.readValue(jsonBody, ExchangeApi.class);
            log.debug(api);

            if (currencyRepository.findByCreatedAt(api.getTimestamp()).isEmpty()) {

                currencyRepository.deleteAll();
                LocalDateTime now = LocalDateTime.now();
                api.getRates().forEach((k,v) -> {
                    this.create(Currency.builder()
                            .name(k)
                            .rate(v)
                            .createdAt(api.getTimestamp())
                            .build());
                });

            }

        }
    }

    public Currency create(Currency currency) {
        return currencyRepository.save(currency);
    }

    public ResultApi convert(RequestApi requestApi) throws AuthenticationException {
        Optional<Login> login = loginRepository.findByToken(requestApi.getToken());
        Currency currencySource = currencyRepository.findByName(requestApi.getCurrencyInput());
        Currency currencyDestination = currencyRepository.findByName(requestApi.getCurrencyOutput());
        Double amountEUR = requestApi.getAmount() / currencySource.getRate();
        Double amountDest = amountEUR * currencyDestination.getRate();

        if (login.isEmpty()) throw new AuthenticationException("Token not found");
        Transaction transaction = transactionRepository.save(Transaction.builder()
                        .currencyInput(currencySource.getName())
                        .currencyOutput(currencyDestination.getName())
                        .amount(requestApi.getAmount())
                        .rate(requestApi.getAmount() / amountDest)
                        .loginId(login.get().getId())
                        .createdAt(Timestamp.from(Instant.now()))
                .build());

        return ResultApi.builder()
                .transactionId(transaction.getId())
                .userId(login.get().getId())
                .currencyInput(currencySource.getName())
                .amountInput(requestApi.getAmount())
                .currencyOutput(currencyDestination.getName())
                .amountOutput(amountDest)
                .rate(requestApi.getAmount() / amountDest)
                .resultDate(Timestamp.from(Instant.now()))
                .build();
    }
}
