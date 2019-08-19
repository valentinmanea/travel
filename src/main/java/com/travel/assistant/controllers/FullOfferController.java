package com.travel.assistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.proxy.dto.FullOfferDto;
import com.travel.assistant.services.FullOfferGenerator;

@RestController
@CrossOrigin
public class FullOfferController {
	
	@Autowired
	FullOfferGenerator generator;
	
	@GetMapping("/full-offer")
	public FullOfferDto getFullOffer(@RequestParam String startCity,@RequestParam String destinationCity) {
		return generator.getFullOffer(startCity, destinationCity);
	}
}
