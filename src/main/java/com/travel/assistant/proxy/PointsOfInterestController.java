package com.travel.assistant.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.travel.assistant.facade.dtos.PointOfInterestDto;

@RestController
@RequestMapping("/points")
public class PointsOfInterestController {
	
	@Value("${interest.point.endpoint}")
	private String endpoint;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AmadeusAuthProxy authProxy;
	
	@GetMapping("/test")
	public ResponseEntity<PointOfInterestDto> getAllPoints(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authProxy.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		System.out.println("endpoint" + endpoint);
		return restTemplate.exchange(
				endpoint + "?latitude=" + latitude +"&longitude=" + longitude, HttpMethod.GET, entity, PointOfInterestDto.class, headers);
	}
	
	@GetMapping("/auth")
	public String getAuthToken(){
		return authProxy.getAuthToken();
	}
}

