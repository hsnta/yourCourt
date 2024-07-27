package com.basketball.drill_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DrillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrillServiceApplication.class, args);
	}

}
