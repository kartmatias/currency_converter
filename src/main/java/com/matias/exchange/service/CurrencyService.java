package com.matias.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matias.exchange.event.CurrencyCreatedEvent;
import com.matias.exchange.model.Currency;
import com.matias.exchange.model.ExchangeApi;
import com.matias.exchange.repository.CurrencyRepository;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

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
    private final ApplicationEventPublisher publisher;

    public CurrencyService(CurrencyRepository currencyRepository, ApplicationEventPublisher publisher) {
        this.currencyRepository = currencyRepository;
        this.publisher = publisher;
    }

    public Flux<Currency> all() {
        return currencyRepository.findAll();
    }

    public Mono<Currency> getByName(String name) {
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
            currencyRepository.deleteAll();

            LocalDate date = LocalDate.now();
            Instant instant = date.atStartOfDay(ZoneId.of("America/Fortaleza")).toInstant();

            api.getRates().forEach((k,v) -> {

                Mono<Currency> res = this.create(Currency.builder()
                                .name(k)
                                .rate(v)
                                .createdAt(instant)
                                .build());
                log.debug(res);

            });

        }


    }

    public Mono<Currency> create(Currency currency) {
        return currencyRepository.save(currency)
                .doOnSuccess(item -> publisher.publishEvent(new CurrencyCreatedEvent(item)));
    }
}
