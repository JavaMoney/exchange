package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExchangeApplication {

	@Bean
	public ObjectMapper configure(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper bean = builder.createXmlMapper(false).build();
		bean.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return bean;
	}

	public static void main(String[] args) {

		SpringApplication.run(ExchangeApplication.class, args);

	}

}
