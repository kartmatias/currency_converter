package com.matias.exchange.controller;

import com.matias.exchange.model.Login;
import com.matias.exchange.service.LoginService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final MediaType mediaType = MediaType.APPLICATION_JSON;
    private final LoginService loginService;

    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public List<Login> getAll() {
        return loginService.all();
    }

    @GetMapping("/{id}")
    public Optional<Login> getById(@PathVariable("id") Long id) {
        return loginService.getById(id);
    }

    @GetMapping("/email/{email}")
    public Optional<Login> getByEmail(@PathVariable("email") String email) {
        return loginService.getByEmail(email);
    }

    @GetMapping("/token/{token}")
    public Optional<Login> getByToken(@PathVariable("token") String token) {
        return loginService.getByToken(token);
    }

    @PostMapping
    public ResponseEntity<Login> create(@RequestBody Login login) {
        Login user = loginService.create(login.getName(), login.getEmail());
        return ResponseEntity.created(URI.create("/user/" + user.getId()))
                .contentType(mediaType)
                .build();

    }

}
