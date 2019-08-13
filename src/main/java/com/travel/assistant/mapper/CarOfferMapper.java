package com.travel.assistant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.assistant.controllers.DateService;
import com.travel.assistant.dtos.CarOfferDto;
import com.travel.assistant.entities.Car;
import com.travel.assistant.entities.CarOffer;

@Service
public class CarOfferMapper {
	
	@Autowired
	DateService dateService;
	
	public CarOffer toEntity(CarOfferDto dto){
		CarOffer carOffer = new CarOffer();
		carOffer.setCity(dto.city);
		carOffer.setCurrency(dto.currency);
		carOffer.setPrice(dto.price);
		carOffer.setStartDate(dateService.toLocalDate(dto.startDate));
		carOffer.setEndDate(dateService.toLocalDate(dto.endDate));
		
		return carOffer;
	}
}
