package com.oxigent.w2m.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SuperheroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperheroesApplication.class, args);
	}

}
