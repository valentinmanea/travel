package com.travel.assistant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.entities.FlightOffer;
import com.travel.assistant.entities.User;
import com.travel.assistant.repo.FlightOfferRepo;
import com.travel.assistant.repo.UserRepo;

@RestController
@RequestMapping("/flight-offer")
public class FlightOfferController {
	
	@Autowired
	FlightOfferRepo repo;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/buy")
	public FlightOffer addFlightOffer(@RequestBody FlightOffer flightOffer) {
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println();
		
		flightOffer.user = user;
		FlightOffer flightOfferSaved = repo.save(flightOffer); 
		user.addFlightOffer(flightOfferSaved);
		return flightOfferSaved;
	}
	
	@GetMapping("/all")
	public List<FlightOffer> getAllFlightOffersBuyedBuyLoggedUser(){
		return userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getAllFlightOffers();

	}
}
