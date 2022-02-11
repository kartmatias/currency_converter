package com.matias.exchange.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

@Data
public class ExchangeApi {
    private boolean success;
    private Timestamp timestamp;
    private String base;
    private Date date;
    private Map<String, Double> rates;
}
