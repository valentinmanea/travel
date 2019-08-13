package com.travel.assistant.proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.travel.assistant.controllers.DateService;
import com.travel.assistant.entities.IataCodeAndCityName;
import com.travel.assistant.proxy.dto.AirportFirstLevelDto;
import com.travel.assistant.proxy.dto.IataCodeDto;
import com.travel.assistant.proxy.dto.flight.FirstLevelFlightDto;
import com.travel.assistant.proxy.dto.flight.FlightSearchDto;
import com.travel.assistant.repo.IataCodeAndCityNameRepo;
import com.travel.assistant.services.SearchService;

@Service
@RestController
@RequestMapping("/flight")
public class FlightProxy {
	
	@Autowired
	IataCodeAndCityNameRepo iataCodeAndCityNameRepo;
	
	Map<String, String> map = new HashMap<>();

	String endpoint = "https://test.api.amadeus.com/v1/reference-data/locations?subType=CITY&keyword=KEYWORD&page[limit]=5";
	String flightsEndpoint = "https://test.api.amadeus.com/v2/shopping/flight-offers";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AmadeusAuthProxy authProxy;
	
	@Autowired
	private DateService dateService;

	@Autowired
	private SearchService searchService;
	
	@GetMapping("/iata-codes")
	public List<IataCodeAndCityName> getAllIataCodes() {
		return iataCodeAndCityNameRepo.findAll();
	}
	
	@GetMapping("/iata-code-by-name")
	public IataCodeAndCityName getIataCodeByName(@RequestParam("name") String name) {
		return iataCodeAndCityNameRepo.findAll().stream().filter(i-> name.equals(i.cityName)).findAny().get();
	}
	
	@PostMapping("/search")
	public FirstLevelFlightDto getFlights(@RequestBody FlightSearchDto dto) {
		HttpHeaders headers = new HttpHeaders();
		System.out.println(dto.adults +" - " + dto.destinationCity + " - " +dto.startCity + " - " + dto.startDate + " - " + dto.endDate);
		String startLocationIataCode = iataCodeAndCityNameRepo
				.findFirstByCityName(dto.startCity)
				.iataCode;
		String fixedEndpoint  = flightsEndpoint + "?originLocationCode=" + startLocationIataCode + "&destinationLocationCode=" + iataCodeAndCityNameRepo.findFirstByCityName(dto.destinationCity).iataCode + "&departureDate=" + dateService.toLocalDate(dto.startDate) + "&returnDate=" + dateService.toLocalDate(dto.endDate) + "&adults=" + dto.adults + "&nonStop=false";
		System.out.println("fixed endpoint" + fixedEndpoint);
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		System.out.println("token " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		searchService.addToHistory("FLIGHT_SEARCH", dto.startCity + "," +  dto.destinationCity);
		return restTemplate.exchange(
				fixedEndpoint, HttpMethod.GET, entity, FirstLevelFlightDto.class, headers).getBody();
	
	}
	@PostConstruct()
	public void execute() {
		iataCodeAndCityNameRepo.deleteAll();
		String alfabet = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i < alfabet.length();i++) {
			alfabet.charAt(i);
			String endpointFixed = endpoint.replace("KEYWORD", alfabet.charAt(i) +"");
			System.out.println("endpoint: " + endpoint);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity(headers);
			List<IataCodeDto> list = restTemplate.exchange(
					endpointFixed, HttpMethod.GET, entity, AirportFirstLevelDto.class, headers).getBody().data;
			for(IataCodeDto item: list) {
				iataCodeAndCityNameRepo.save(new IataCodeAndCityName(item.iataCode,item.address.cityName));
			}
		}
	}
}
