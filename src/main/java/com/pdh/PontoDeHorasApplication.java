package com.pdh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class PontoDeHorasApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(PontoDeHorasApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PontoDeHorasApplication.class);
	}

}
