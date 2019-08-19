package com.travel.assistant.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.assistant.proxy.FlightProxy;
import com.travel.assistant.proxy.HotelProxy;
import com.travel.assistant.proxy.dto.FullOfferDto;
import com.travel.assistant.proxy.dto.HotelSecondLevelDto;
import com.travel.assistant.proxy.dto.flight.FlightSecondLevelDto;

@Service
public class FullOfferGenerator {
	
	
	@Autowired
	FlightProxy flightProxy;
	
	@Autowired
	HotelProxy hotelProxy;
	
	@Autowired
	private CityNameGeneratorByHistory cityNameGeneratorByHistory;
	
	public FullOfferDto getFullOffer(String startCity, String destinationCity) {
		FullOfferDto fullOfferDto = new FullOfferDto();
		if(destinationCity.equals("Random destination")){
			destinationCity = cityNameGeneratorByHistory.generateCityName();
			System.out.println("generated city name: "+ destinationCity);
		}
		Optional<HotelSecondLevelDto> hotelOptional = hotelProxy.getSingleHotelsInACity(destinationCity);
		if(hotelOptional.isPresent()) {
			Optional<FlightSecondLevelDto> flightOptional = flightProxy.getSingleFlightInACity(startCity, destinationCity);
			if(flightOptional.isPresent()) {
				fullOfferDto.flightSecondLevelDto = flightOptional.get();
				fullOfferDto.hotelSecondLevelDto = hotelOptional.get();
			}
		}
		return fullOfferDto;
	}
}
