package com.desafio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	private static Logger Logger = LoggerFactory.getLogger(MainApplication.class);

	public static void main(String[] args) {
		Logger.info("Inicializando API");
		SpringApplication.run(MainApplication.class, args);
		Logger.info("API Inicializada");
	}

}
