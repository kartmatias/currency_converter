package com.matias.exchange.repository;

import com.matias.exchange.model.Currency;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CurrencyRepository extends CrudRepository<Currency,Long> {

    Currency findByName(String name);

    List<Currency> findByCreatedAt(Timestamp createdAt);

}
