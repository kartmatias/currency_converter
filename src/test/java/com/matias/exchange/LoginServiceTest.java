package com.matias.exchange;

import com.matias.exchange.repository.LoginRepository;
import com.matias.exchange.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Log4j2
@Import({LoginService.class, LoginRepository.class})
public class LoginServiceTest {

    private final LoginService loginService;
    private final LoginRepository loginRepository;

    public LoginServiceTest(@Autowired LoginService loginService,
                            @Autowired LoginRepository loginRepository) {
        this.loginService = loginService;
        this.loginRepository = loginRepository;
    }

}
