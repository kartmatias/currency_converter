package com.matias.exchange.event;

import com.matias.exchange.model.Currency;
import org.springframework.context.ApplicationEvent;

public class CurrencyCreatedEvent extends ApplicationEvent {

    public CurrencyCreatedEvent(Currency source) {
        super(source);
    }
}
