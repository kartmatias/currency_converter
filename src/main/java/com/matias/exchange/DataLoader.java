package com.matias.exchange;

import com.matias.exchange.model.Login;
import com.matias.exchange.repository.LoginRepository;
import com.matias.exchange.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class DataLoader  implements ApplicationRunner {

    private LoginRepository loginRepository;
    private CurrencyService currencyService;

    @Autowired
    public DataLoader(LoginRepository loginRepository, CurrencyService currencyService) {
        this.loginRepository = loginRepository;
        this.currencyService = currencyService;
    }


    @Override
    public void run(ApplicationArguments arguments) {
        log.debug(arguments);
        loginRepository.save(Login.builder()
                .name("Novo Default User")
                .email("default@gmail.com")
                .token("123456789")
                .build());

        try {
            currencyService.updateCurrency();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
