package com.travel.assistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.entities.FlightOffer;
import com.travel.assistant.entities.FullOffer;
import com.travel.assistant.entities.HotelOffer;
import com.travel.assistant.entities.User;
import com.travel.assistant.proxy.dto.FullOfferDto;
import com.travel.assistant.repo.FlightOfferRepo;
import com.travel.assistant.repo.FullOfferRepo;
import com.travel.assistant.repo.HotelOfferRepo;
import com.travel.assistant.repo.UserRepo;
import com.travel.assistant.services.FullOfferGenerator;

@RestController
@CrossOrigin
public class FullOfferController {
	
	@Autowired
	FullOfferGenerator generator;
	
	@Autowired
	HotelOfferRepo hotelOfferRepo;
	
	@Autowired
	FlightOfferRepo flightOfferRepo;
	
	@Autowired
	FullOfferRepo fullOfferRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/full-offer")
	public FullOfferDto getFullOffer(@RequestParam String startCity,@RequestParam String destinationCity) {
		return generator.getFullOffer(startCity, destinationCity);
	}
	
	@PostMapping("/full-offer/buy")
	public void buyFullOffer(@RequestBody FullOffer fullOffer) {
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		HotelOffer hotelOffer = fullOffer.getHotelOffer();
		hotelOffer.user = user;
		hotelOfferRepo.save(hotelOffer);
		FlightOffer flightOffer = fullOffer.getFlightOffer();
		flightOffer.user = user;
		flightOfferRepo.save(flightOffer);
		FullOffer fullOfferSaved = fullOfferRepo.save(fullOffer);
		fullOfferSaved.setFlightOffer(flightOffer);
		fullOfferSaved.setHotelOffer(hotelOffer);
	}
}
