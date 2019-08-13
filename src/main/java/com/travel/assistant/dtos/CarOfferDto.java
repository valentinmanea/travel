package com.travel.assistant.dtos;

import com.travel.assistant.entities.Car;
import com.travel.assistant.enums.Currency;
import com.travel.assistant.facade.dtos.DateDto;

public class CarOfferDto {
	 public long id;
	
	 public DateDto startDate;

	 public DateDto endDate;
	
	 public String city;
	
	 public double price;
	
	 public Currency currency;
	
	 public Car car;
}
