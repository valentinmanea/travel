package com.travel.assistant;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.travel.assistant.entities.Hotel;
import com.travel.assistant.entities.HousingReservation;

@SpringBootApplication
public class TravelAssistantApplication extends SpringBootServletInitializer implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TravelAssistantApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
