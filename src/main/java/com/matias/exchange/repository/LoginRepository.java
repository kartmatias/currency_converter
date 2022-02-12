package com.matias.exchange.repository;

import com.matias.exchange.model.Login;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoginRepository extends CrudRepository<Login, Long> {
    Optional<Login> findByEmail(String email);
    Optional<Login> findByToken(String token);
}
