package com.matias.exchange.repository;

import com.matias.exchange.model.Transaction;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TransactionRepository extends R2dbcRepository<Transaction,Long> {
}
