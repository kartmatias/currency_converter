package com.matias.exchange.service;

import com.matias.exchange.event.UserCreatedEvent;
import com.matias.exchange.model.Login;
import com.matias.exchange.repository.LoginRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Log4j2
@Service
public class LoginService {

    private final ApplicationEventPublisher publisher;
    private final LoginRepository loginRepository;

    public LoginService(ApplicationEventPublisher publisher, LoginRepository loginRepository) {
        this.publisher = publisher;
        this.loginRepository = loginRepository;
    }

    public Flux<Login> all() {
        return loginRepository.findAll();
    }

    public Mono<Login> getById(Long id) {
        return loginRepository.findById(id);
    }

    public Mono<Login> getByToken(String token) {
        return loginRepository.findByToken(token);
    }

    public Mono<Login> getByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    public Mono<Login> create(String name, String email) {
        return loginRepository.save(
                new Login(null,name, email, UUID.randomUUID().toString())
        ).doOnSuccess(user -> publisher.publishEvent(new UserCreatedEvent(user)));
    }

}
