package com.realdolmen.mycareer.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleApplication.class, args);
	}

}
