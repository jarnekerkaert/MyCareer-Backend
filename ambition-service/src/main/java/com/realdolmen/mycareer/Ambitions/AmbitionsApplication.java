package com.realdolmen.mycareer.Ambitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AmbitionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmbitionsApplication.class, args);
	}

}
