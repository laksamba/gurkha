package com.gurkha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@Configuration
@EnableWebSecurity
public class GurkhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GurkhaApplication.class, args);
	}
}

