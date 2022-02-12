package com.matias.exchange.controller;

import com.matias.exchange.model.Currency;
import com.matias.exchange.model.RequestApi;
import com.matias.exchange.model.ResultApi;
import com.matias.exchange.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
    public List<Currency> getAll(){
        return currencyService.all();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Currency> getById(@PathVariable("name") String name) {
        return ResponseEntity.ok().body(currencyService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<Currency> create(@RequestBody Currency currency) {
        LocalDateTime now = LocalDateTime.now();

        Currency c = currencyService.create(Currency.builder()
                .name(currency.getName())
                .rate(currency.getRate())
                .createdAt(Timestamp.valueOf(now))
                .build());
        return ResponseEntity.created(URI.create("/currency/"+ c.getName()))
                .contentType(mediaType)
                .build();
    }

    @PostMapping("/convert")
    public ResponseEntity<ResultApi> convert(@RequestBody RequestApi requestApi) {
        try {
            return ResponseEntity.ok().body(currencyService.convert(requestApi));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
