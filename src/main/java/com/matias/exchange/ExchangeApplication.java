package com.matias.exchange;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@Log4j2
public class ExchangeApplication {

//	@Autowired
//	private DataLoader dataLoader;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApplication.class, args);
	}

//	@Bean
//	InitializingBean loadData() {
//		log.debug("Initializing ...");
//		return () -> {
//			dataLoader.run(null);
//		};
//	}
}


