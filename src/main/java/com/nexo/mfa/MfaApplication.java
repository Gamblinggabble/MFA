package com.nexo.mfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfaApplication.class, args);
		System.out.println("Hello, Nexo!");
	}

}