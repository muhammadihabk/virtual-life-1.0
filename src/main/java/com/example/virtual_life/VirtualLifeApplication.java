package com.example.virtual_life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@EnableJpaAuditing
public class VirtualLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualLifeApplication.class, args);
	}

}
