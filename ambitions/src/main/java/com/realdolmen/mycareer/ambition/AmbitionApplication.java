package com.realdolmen.mycareer.ambition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AmbitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmbitionApplication.class, args);
	}

}
