package com.empresa.curso.ProyectoGym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:integration/fitgeneration-feed.xml")
public class ProyectoGymApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoGymApplication.class, args);
	}

}
