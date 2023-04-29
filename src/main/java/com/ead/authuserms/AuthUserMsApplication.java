package com.ead.authuserms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AuthUserMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthUserMsApplication.class, args);
	}

}
