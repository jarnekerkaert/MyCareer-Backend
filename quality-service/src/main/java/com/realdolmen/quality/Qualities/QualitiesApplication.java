package com.realdolmen.quality.Qualities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QualitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QualitiesApplication.class, args);
	}

}
