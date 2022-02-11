package com.matias.exchange.repository;

import com.matias.exchange.model.Login;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface LoginRepository extends R2dbcRepository<Login, Long> {
    Mono<Login> findByEmail(String email);
    Mono<Login> findByToken(String token);
}
