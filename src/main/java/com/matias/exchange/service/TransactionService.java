package com.matias.exchange.service;

import com.matias.exchange.model.Login;
import com.matias.exchange.model.Transaction;
import com.matias.exchange.repository.TransactionRepository;
import com.matias.exchange.repository.LoginRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(ApplicationEventPublisher publisher, TransactionRepository transactionRepository, LoginRepository loginRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> all() {
        return StreamSupport
                .stream(transactionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


}
