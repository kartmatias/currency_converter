package com.matias.exchange.repository;

import com.matias.exchange.model.Currency;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CurrencyRepository extends R2dbcRepository<Currency,Long> {

    Mono<Currency> findByName(String token);

}
