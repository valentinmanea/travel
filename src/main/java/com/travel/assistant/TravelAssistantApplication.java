package com.travel.assistant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TravelAssistantApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TravelAssistantApplication.class, args);
	}
}
