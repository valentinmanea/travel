package com.travel.assistant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.entities.HotelOffer;
import com.travel.assistant.entities.User;
import com.travel.assistant.repo.HotelOfferRepo;
import com.travel.assistant.repo.UserRepo;

@RestController
@RequestMapping("/hotel-offer")
public class HotelOfferController {
	
	@Autowired
	HotelOfferRepo repo;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/buy")
	public HotelOffer addHotelOffer(@RequestBody HotelOffer hotelOffer) {
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println();
		
		hotelOffer.user = user;
		HotelOffer hotelOfferSaved = repo.save(hotelOffer); 
		user.addHotelOffer(hotelOfferSaved);
		return hotelOfferSaved;
	}
	
	@GetMapping("/all")
	public List<HotelOffer> getAllHotelOffersBuyedBuyLoggedUser(){
		return userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getAllHotelOffers();

	}
}
