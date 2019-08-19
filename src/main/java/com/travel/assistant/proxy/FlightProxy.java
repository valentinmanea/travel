package com.travel.assistant.proxy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.travel.assistant.proxy.dto.flight.FlightFirstLevelDto;
import com.travel.assistant.proxy.dto.flight.FlightSearchDto;
import com.travel.assistant.proxy.dto.flight.FlightSecondLevelDto;
import com.travel.assistant.repo.IataCodeAndCityNameRepo;
import com.travel.assistant.services.DateService;
import com.travel.assistant.services.IataCodeGeneratorByHistory;
import com.travel.assistant.services.SearchService;

@Service
@RestController
@RequestMapping("/flight")
public class FlightProxy {
	
	@Autowired
	IataCodeAndCityNameRepo iataCodeAndCityNameRepo;
	
	Map<String, String> map = new HashMap<>();

	String flightsEndpoint = "https://test.api.amadeus.com/v2/shopping/flight-offers";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AmadeusAuthProxy authProxy;
	
	@Autowired
	private DateService dateService;

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private IataCodeGeneratorByHistory iataCodeGeneratorByHistory;
	
	@PostMapping("/search")
	public FlightFirstLevelDto getFlights(@RequestBody FlightSearchDto dto) {
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
		searchService.addToHistory("FLIGHT_SEARCH", dto.destinationCity);
		return restTemplate.exchange(
				fixedEndpoint, HttpMethod.GET, entity, FlightFirstLevelDto.class, headers).getBody();
	
	}


	public Optional<FlightSecondLevelDto> getSingleFlightInACity(String startCity, String destinationCity) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);

		
		String startLocationIataCode = iataCodeAndCityNameRepo.findFirstByCityName(startCity).iataCode;
		String iataCodeForDestinationCity = iataCodeAndCityNameRepo.findFirstByCityName(destinationCity).iataCode;

		System.out.println("Pentru flight am gasit random: " +  iataCodeAndCityNameRepo.findFirstByIataCode(iataCodeForDestinationCity).cityName);
		
		Random random = new Random();
		LocalDate startDate = LocalDate.now().plusDays(random.nextInt(30));
		LocalDate endDate = startDate.plusDays(random.nextInt(7));
		
		
		String fixedEndpoint  = flightsEndpoint + "?originLocationCode=" + startLocationIataCode + "&destinationLocationCode=" + iataCodeForDestinationCity + "&departureDate=" + startDate + "&returnDate=" + endDate + "&adults=" + 2 + "&nonStop=false";
		
		System.out.println("fixed endpoint" + fixedEndpoint);
		
		
		System.out.println("token " + authProxy.getAuthToken());
		
		
		FlightFirstLevelDto first = restTemplate.exchange(
				fixedEndpoint, HttpMethod.GET, entity, FlightFirstLevelDto.class, headers).getBody();
		if(first != null) {
			int count = 0;
			while(first.data == null && count < 20 ) {
				LocalDate startDateNew = LocalDate.now().plusDays(random.nextInt(15)+1);
				System.out.println("startDateNew: "+ startDateNew);
				LocalDate endDateNew = startDateNew.plusDays(random.nextInt(4)+1);
				System.out.println("endDateNew: "+ endDateNew);

				fixedEndpoint = fixedEndpoint.replace(startDate.toString(), startDateNew.toString());
				fixedEndpoint = fixedEndpoint.replace(endDate.toString(), endDateNew.toString());
				startDate = startDateNew;
				endDate = endDateNew;
				System.out.println("fixed endpoint in while, count= " + count + fixedEndpoint);
				first = restTemplate.exchange(
						fixedEndpoint, HttpMethod.GET, entity, FlightFirstLevelDto.class, headers).getBody();
				count++;
			} 
			if(first.data != null) {
				return first.data.stream().findAny();
			}
		}
			return Optional.empty();
	}
	
}
