package com.matias.exchange.service;

import com.matias.exchange.model.Login;
import com.matias.exchange.repository.LoginRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class LoginService {

    private final ApplicationEventPublisher publisher;
    private final LoginRepository loginRepository;

    public LoginService(ApplicationEventPublisher publisher, LoginRepository loginRepository) {
        this.publisher = publisher;
        this.loginRepository = loginRepository;
    }

    public List<Login> all() {
        return StreamSupport
                .stream(loginRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Login> getById(Long id) {
        return loginRepository.findById(id);
    }

    public Optional<Login> getByToken(String token) {
        return loginRepository.findByToken(token);
    }

    public Optional<Login> getByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    public Login create(String name, String email) {
        return loginRepository.save(new Login(null,name, email, UUID.randomUUID().toString()));
    }

}
