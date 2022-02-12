package com.matias.exchange.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ResultApi {

    private Long transactionId;
    private Long userId;
    private String currencyInput;
    private Double amountInput;
    private String currencyOutput;
    private Double amountOutput;
    private Double rate;
    private Timestamp resultDate;

}
