package com.example.melichallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MelichallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MelichallengeApplication.class, args);
	}

}
