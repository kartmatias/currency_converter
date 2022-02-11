package com.matias.exchange.controller;

import com.matias.exchange.model.Currency;
import com.matias.exchange.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
        try {
            currencyService.updateCurrency();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return currencyService.all();
    }

}
