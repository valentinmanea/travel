package com.travel.assistant.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.travel.assistant.facade.dtos.PointOfInterestDataDto;
import com.travel.assistant.facade.dtos.PointOfInterestDto;
import com.travel.assistant.proxy.dto.CityDto;

@RestController
@RequestMapping("/points")
public class PointsOfInterestProxy {
	
	@Value("${interest.point.endpoint}")
	private String endpoint;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AmadeusAuthProxy authProxy;
	
	@Autowired
	CitiesProxy citiesProxy;
	
	@GetMapping("/test")
	public PointOfInterestDto getAllPoints(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		System.out.println("token: " +  authProxy.getAuthToken());
		System.out.println("debug: "+ endpoint + "?latitude=" + latitude +"&longitude=" + longitude);
		return restTemplate.exchange(
				endpoint + "?latitude=" + latitude +"&longitude=" + longitude, HttpMethod.GET, entity, PointOfInterestDto.class, headers).getBody();
	}
	
	@GetMapping("/auth")
	public String getAuthToken(){
		return authProxy.getAuthToken();
	}
	
	@GetMapping("/test/random")
	public List<PointOfInterestDataDto> getPointsOfInterestByRandomCity(){
		CityDto cityDto = citiesProxy.getRandomCity();
		try {
			return getAllPoints(cityDto.latitude, cityDto.longitude).data;
		}catch(Exception e) {
			throw new IllegalArgumentException("N am gasit nimic");
		}
	}
}

