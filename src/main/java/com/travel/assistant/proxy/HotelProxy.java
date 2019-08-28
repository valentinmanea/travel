package com.travel.assistant.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.travel.assistant.services.CityNameGeneratorByHistory;
import com.travel.assistant.services.SearchService;


@Service
@RestController
public class HotelProxy {
	@Autowired
	AmadeusAuthProxy authProxy;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CitiesProxy cityProxy;
	
	@Autowired
	private SearchService searchService;


	
	@GetMapping("/hotels")
	public HotelFirstLevelDto getHotels() {
		String endpoint = "https://test.api.amadeus.com/v2/shopping/hotel-offers?page=1&latitude=LATITUDE&radius=300&radiusUnit=KM&longitude=LONGITUDE&radius=300&radiusUnit=KM";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		return restTemplate.exchange(
				endpoint, HttpMethod.GET, entity, HotelFirstLevelDto.class, headers).getBody();
	}
	@GetMapping("/hotels-city")
	public HotelFirstLevelDto getHotelsInACity(@RequestParam("cityName") String cityName) {
		String endpoint = "https://test.api.amadeus.com/v2/shopping/hotel-offers?page=1&latitude=LATITUDE&radius=300&radiusUnit=KM&longitude=LONGITUDE&radius=300&radiusUnit=KM";
		CityDto city = cityProxy.getCity(cityName);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		endpoint = endpoint.replace("LATITUDE", city.latitude);
		endpoint = endpoint.replace("LONGITUDE", city.longitude);
		searchService.addToHistory("HOTEL_SEARCH", cityName);
		System.out.println("endpoint for hotel" + endpoint + "cityName" + cityName);
		return restTemplate.exchange(
				endpoint, HttpMethod.GET, entity, HotelFirstLevelDto.class, headers).getBody();
	}
	public Optional<HotelSecondLevelDto> getSingleHotelsInACity(@RequestParam("cityName") String cityName) {
		String endpoint = "https://test.api.amadeus.com/v2/shopping/hotel-offers?page=1&latitude=LATITUDE&radius=300&radiusUnit=KM&longitude=LONGITUDE&radius=300&radiusUnit=KM";
		CityDto city;
			city = cityProxy.getCity(cityName);
		if(city == null) {
			throw new RuntimeException("City not found");
		} 
		System.out.println("Pentru hotel am gasit random un hotel pe nume: " + city.name);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		endpoint = endpoint.replace("LATITUDE", city.latitude);
		endpoint = endpoint.replace("LONGITUDE", city.longitude);
		return restTemplate.exchange(
				endpoint, HttpMethod.GET, entity, HotelFirstLevelDto.class, headers).getBody().hotelSecondLevelDto.stream().findAny();
	}
	
}
