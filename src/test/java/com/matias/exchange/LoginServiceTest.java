package com.matias.exchange;

import com.matias.exchange.model.Login;
import com.matias.exchange.repository.LoginRepository;
import com.matias.exchange.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

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

    @Test
    public void getAll() {
        Flux<Login> userFlux = loginRepository.saveAll(Flux.just(
                new Login(null, "Carlos", "carlos@gmail.com", "xkxkxkxkxk"),
                new Login(null, "Jose", "jose@gmail.com", "234234l23k4ç32lk4"),
                new Login(null, "Antonio", "antonio@gmail.com", "lsçdfklçsdkfsçdlk")
                ));

        Flux<Login> composite = loginService.all().thenMany(userFlux);

        Predicate<Login> match = user -> userFlux.any(saveItem -> saveItem.equals(user)).block();

        StepVerifier
                .create(composite)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .verifyComplete();
    }

}
