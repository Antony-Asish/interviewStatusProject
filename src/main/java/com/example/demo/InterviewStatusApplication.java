package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
public class InterviewStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewStatusApplication.class, args);
		System.out.println("SERVER STARTED");
	}

}
