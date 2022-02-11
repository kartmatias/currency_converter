package com.matias.exchange.repository;

import com.matias.exchange.model.Currency;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CurrencyRepository extends ReactiveCrudRepository<Currency,Long> {

    Mono<Currency> findByName(String token);

}
