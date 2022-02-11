package com.matias.exchange.controller;

import com.matias.exchange.model.Login;
import com.matias.exchange.service.LoginService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final MediaType mediaType = MediaType.APPLICATION_JSON;
    private final LoginService loginService;

    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public Publisher<Login> getAll() {
        return loginService.all();
    }

    @GetMapping("/{id}")
    public Publisher<Login> getById(@PathVariable("id") Long id) {
        return loginService.getById(id);
    }

    @GetMapping("/email/{email}")
    public Publisher<Login> getByEmail(@PathVariable("email") String email) {
        return loginService.getByEmail(email);
    }

    @GetMapping("/token/{token}")
    public Publisher<Login> getByToken(@PathVariable("token") String token) {
        return loginService.getByToken(token);
    }

    @PostMapping
    public Publisher<ResponseEntity<Login>> create(@RequestBody Login login) {
        return loginService.create(login.getName(), login.getEmail())
                .map(u -> ResponseEntity.created(URI.create("/user/" + u.getId()))
                        .contentType(mediaType)
                        .build()
                );
    }

}
