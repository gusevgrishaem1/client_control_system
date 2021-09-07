package com.techls.fdoClientsManagerWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FdoClientsManagerWebApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FdoClientsManagerWebApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(FdoClientsManagerWebApp.class);
	 }
}
