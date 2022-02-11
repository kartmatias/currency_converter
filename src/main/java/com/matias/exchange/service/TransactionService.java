package com.matias.exchange.service;

import com.matias.exchange.model.Login;
import com.matias.exchange.model.Transaction;
import com.matias.exchange.repository.TransactionRepository;
import com.matias.exchange.repository.LoginRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class TransactionService {

    private final ApplicationEventPublisher publisher;
    private final TransactionRepository transactionRepository;
    private final LoginRepository loginRepository;

    public TransactionService(ApplicationEventPublisher publisher, TransactionRepository transactionRepository, LoginRepository loginRepository) {
        this.publisher = publisher;
        this.transactionRepository = transactionRepository;
        this.loginRepository = loginRepository;
    }

    public Flux<Transaction> all() {
        return transactionRepository.findAll();
    }

    public Mono<Transaction> create(String token) {
        Login login = loginRepository.findByToken(token).block();
        Transaction transaction = Transaction.builder()
                .userId(login.getId())
                .currencyInput("")
                .build();
        return transactionRepository.save(transaction);
    }


}
