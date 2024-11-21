package com.cartorio.api.cartorio_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.cartorio.api.cartorio_api.entity")
public class CartorioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartorioApiApplication.class, args);
	}

}
