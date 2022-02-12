package com.matias.exchange.repository;

import com.matias.exchange.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency,Long> {

    Currency findByName(String token);

}
