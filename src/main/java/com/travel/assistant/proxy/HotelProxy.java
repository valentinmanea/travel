package com.travel.assistant.proxy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.travel.assistant.proxy.dto.CityDto;
import com.travel.assistant.proxy.dto.HotelFirstLevelDto;
import com.travel.assistant.proxy.dto.HotelSecondLevelDto;
import com.travel.assistant.services.SearchService;


@Service
@RestController
public class HotelProxy {
	@Autowired
	AmadeusAuthProxy authProxy;
	
	String endpoint = "https://test.api.amadeus.com/v2/shopping/hotel-offers?page=1&latitude=LATITUDE&radius=300&radiusUnit=KM&longitude=LONGITUDE&radius=300&radiusUnit=KM";
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CitiesProxy cityProxy;
	
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/hotels")
	public HotelFirstLevelDto getHotels() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		return restTemplate.exchange(
				endpoint, HttpMethod.GET, entity, HotelFirstLevelDto.class, headers).getBody();
	}
	@GetMapping("/hotels-city")
	public HotelFirstLevelDto getHotelsInACity(@RequestParam("cityName") String cityName) {
		CityDto city = cityProxy.getCity(cityName);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		endpoint = endpoint.replace("LATITUDE", city.latitude);
		endpoint = endpoint.replace("LONGITUDE", city.longitude);
		searchService.addToHistory("HOTEL_SEARCH", cityName);
		return restTemplate.exchange(
				endpoint, HttpMethod.GET, entity, HotelFirstLevelDto.class, headers).getBody();
	}
	
	@GetMapping("/random-hotels")
	public HotelFirstLevelDto getRandomHotelsIn3Cities() {
		HotelFirstLevelDto firstLevelDto = new HotelFirstLevelDto();
		List<HotelSecondLevelDto> hotels = new ArrayList<>();
		for(int i = 0;i< 3; i++) {
			System.out.println("For: " + i);
			CityDto city = cityProxy.getRandomCity();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity(headers);
			endpoint = endpoint.replace("LATITUDE", city.latitude);
			endpoint = endpoint.replace("LONGITUDE", city.longitude);
			hotels.addAll(restTemplate.exchange(
					endpoint, HttpMethod.GET, entity, HotelFirstLevelDto.class, headers).getBody().hotelSecondLevelDto);
			endpoint = endpoint.replace(city.latitude,"LATITUDE" );
			endpoint = endpoint.replace(city.longitude,"LONGITUDE");
		}
		firstLevelDto.hotelSecondLevelDto = hotels;
		return firstLevelDto;
	}
	
}
