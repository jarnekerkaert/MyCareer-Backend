package com.realdolmen.mycareer.quality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QualityApplication {

	public static void main(String[] args) {
		SpringApplication.run(QualityApplication.class, args);
	}

}
