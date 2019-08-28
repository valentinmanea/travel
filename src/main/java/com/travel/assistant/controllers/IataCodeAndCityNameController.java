package com.travel.assistant.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.travel.assistant.entities.IataCodeAndCityName;
import com.travel.assistant.proxy.AmadeusAuthProxy;
import com.travel.assistant.proxy.dto.AirportFirstLevelDto;
import com.travel.assistant.proxy.dto.IataCodeDto;
import com.travel.assistant.repo.IataCodeAndCityNameRepo;

@RestController
public class IataCodeAndCityNameController {
	private static final String endpoint = "https://test.api.amadeus.com/v1/reference-data/locations?subType=CITY&keyword=KEYWORD&page[limit]=5";


	@Autowired
	IataCodeAndCityNameRepo iataCodeAndCityNameRepo;
	
	@Autowired
	private AmadeusAuthProxy authProxy;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/iata-codes")
	public List<IataCodeAndCityName> getAllIataCodes() {
		List<IataCodeAndCityName> list = iataCodeAndCityNameRepo.findAll();
		list.add(0, new IataCodeAndCityName("", "Random destination"));
		return list;
	}
	
	@GetMapping("/iata-code-by-name")
	public IataCodeAndCityName getIataCodeByName(@RequestParam("name") String name) {
		return iataCodeAndCityNameRepo.findAll().stream().filter(i-> name.equals(i.cityName)).findAny().get();
	}
	
	@PostConstruct()
	public void execute() {
		int contor = 0;
		iataCodeAndCityNameRepo.deleteAll();
		String alfabet = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i < alfabet.length();i++) {
			System.out.println("i= " + i);
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
				System.out.println("contor= "+ contor++);
				iataCodeAndCityNameRepo.save(new IataCodeAndCityName(item.iataCode,item.address.cityName));
			}
		}
	}
}
