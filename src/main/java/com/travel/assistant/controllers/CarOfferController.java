package com.travel.assistant.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.dtos.CarOfferDto;
import com.travel.assistant.dtos.CarOfferSearchDto;
import com.travel.assistant.entities.Car;
import com.travel.assistant.entities.CarOffer;
import com.travel.assistant.entities.User;
import com.travel.assistant.mapper.CarOfferMapper;
import com.travel.assistant.repo.UserRepo;
import com.travel.assistant.services.SearchService;

@RestController
@RequestMapping("car-offer")
public class CarOfferController {
	
	@Autowired
	CarOfferRepo repo;
	
	@Autowired
	CarRepo carRepo;
	@Autowired
	CarOfferMapper mapper;
	
	@Autowired
	DateService dateService;
	@Autowired
	SearchService searchService;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/add")
	public CarOffer addOffer(@RequestBody CarOfferDto carOfferDto){
		System.out.println("CarOffer: " + carOfferDto);
		Car car = carOfferDto.car;
		System.out.println("Car: " + car);
		car = carRepo.save(car);
		CarOffer carOffer = mapper.toEntity(carOfferDto);
		carOffer.setCar(car);
		CarOffer carOfferSaved = repo.save(carOffer);
		car.setCarOffer(carOfferSaved);
		return carOfferSaved;
	}
	
	@PostMapping("/search")
	public List<CarOffer> addOffer(@RequestBody CarOfferSearchDto carOfferSearchDto){
		LocalDate startDate = dateService.toLocalDate(carOfferSearchDto.selectedStartDate);
		LocalDate endDate = dateService.toLocalDate(carOfferSearchDto.selectedEndDate);
		searchService.addToHistory("CAR_OFFER", carOfferSearchDto.selectedCity);
		return repo.findCarOffersBetween(startDate, endDate, carOfferSearchDto.selectedCity).stream().filter(offer->offer.getRentalUser() == null).collect(Collectors.toList());
	}
	
	@PostMapping("/buy")
	public void buyOffer(@RequestBody long id){ 
		Optional<CarOffer> carOffer = repo.findById(id);
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		carOffer.get().setRentalUser(user);
		user.buyCarOffer(carOffer.get());
		repo.save(carOffer.get());
	}
	@GetMapping("/get-bought")
	public List<CarOffer> getBoughtOffers(){ 
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return user.getAllCarOffers();
	}
	
}
