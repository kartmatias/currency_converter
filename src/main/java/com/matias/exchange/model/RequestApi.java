package com.matias.exchange.model;

import lombok.Data;

@Data
public class RequestApi {
    private String token;
    private String currencyInput;
    private String currencyOutput;
    private Double amount;
}
