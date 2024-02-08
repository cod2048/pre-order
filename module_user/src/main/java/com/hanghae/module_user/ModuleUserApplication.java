package com.hanghae.module_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ModuleUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuleUserApplication.class, args);
	}

}