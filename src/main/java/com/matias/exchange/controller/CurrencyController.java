package com.matias.exchange.controller;

import com.matias.exchange.model.Currency;
import com.matias.exchange.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Log4j2
@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

    private final MediaType mediaType = MediaType.APPLICATION_JSON;
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public Publisher<Currency> getAll(){
        return currencyService.all();
    }

    @GetMapping("/{name}")
    public Publisher<Currency> getById(@PathVariable("name") String name) {
        return currencyService.getByName(name);
    }

    @PostMapping
    public Publisher<ResponseEntity<Currency>> create(@RequestBody Currency currency) {
        LocalDate date = LocalDate.now();
        Instant instant = date.atStartOfDay(ZoneId.of("America/Fortaleza")).toInstant();
        return currencyService.create(Currency.builder()
                        .name(currency.getName())
                        .rate(currency.getRate())
                        .createdAt(instant)
                        .build())
                .map(c -> ResponseEntity.created(URI.create("/currency/"+ c.getName()))
                        .contentType(mediaType)
                        .build()
                );
    }

}
