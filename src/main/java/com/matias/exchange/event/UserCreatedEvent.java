package com.matias.exchange.event;

import com.matias.exchange.model.Login;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {

    public UserCreatedEvent(Login source) {
        super(source);
    }
}
