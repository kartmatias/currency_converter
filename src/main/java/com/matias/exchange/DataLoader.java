package com.matias.exchange;

import com.matias.exchange.service.CurrencyService;
import com.matias.exchange.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class DataLoader  implements ApplicationRunner {

    private CurrencyService currencyService;
    private LoginService loginService;

    @Autowired
    public DataLoader(CurrencyService currencyService, LoginService loginService) {
        this.currencyService = currencyService;
        this.loginService = loginService;
    }


    @Override
    public void run(ApplicationArguments arguments) {

        loginService.create("carlos","kartmatias@gmail.com");

        try {
            currencyService.updateCurrency();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
