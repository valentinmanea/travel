package com.travel.assistant.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
import com.travel.assistant.proxy.dto.GeoDbDto;

@Service
@RestController
public class CitiesProxy {
		
	String countEndpoint = "http://geodb-free-service.wirefreethought.com/v1/geo/cities?limit=1&hateoasMode=off";
	String getCityEndpoint = "http://geodb-free-service.wirefreethought.com/v1/geo/cities?limit=1&offset=OFFSET&hateoasMode=off";
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/random-city")
	public CityDto getRandomCity(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		String endpointProcessed = addOffset(getCityEndpoint);
		CityDto cityDto = restTemplate.exchange(
				endpointProcessed, HttpMethod.GET, entity, GeoDbDto.class, headers).getBody().data.stream().findFirst().get();
		return cityDto;
	}
	@GetMapping("/cities")
	public List<CityDto> getAllCities(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
//		String endpointProcessed = addOffset(getCityEndpoint);
		return restTemplate.exchange(
				countEndpoint, HttpMethod.GET, entity, GeoDbDto.class, headers).getBody().data;
	}
	@GetMapping("/city")
	public CityDto getCity(@RequestParam("cityName") String cityName){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
//		String endpointProcessed = addOffset(getCityEndpoint);
		return restTemplate.exchange(
				countEndpoint + "&namePrefix=" + cityName, HttpMethod.GET, entity, GeoDbDto.class, headers).getBody().data.get(0);
	}
	String addOffset(String getCityEndpoint){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		String count = restTemplate.exchange(
				countEndpoint, HttpMethod.GET, entity, GeoDbDto.class, headers).getBody().metaData.totalCount;
		Random r = new Random();
		Integer offset = r.nextInt(Integer.parseInt(count)-1);
		return getCityEndpoint.replace("OFFSET", offset.toString());
	}
	@GetMapping("/cities/10")
	List<CityDto> get10RandomCities(){
		List<CityDto> cities = new ArrayList<CityDto>(); 
		for(int i = 0; i<10 ;i++) {
			cities.add(getRandomCity());
		}
		return cities.stream().distinct().collect(Collectors.toList());
 	}
}
