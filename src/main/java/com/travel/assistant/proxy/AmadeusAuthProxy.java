package com.travel.assistant.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.travel.assistant.facade.dtos.AuthResponseDto;

@Service
public class AmadeusAuthProxy {
	
	@Value("${client.id}")
	String clientId;

	@Value("${client.secret}")
	String clientSecret;
	
	@Value("${grant.type}")
	String grantType;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public String getAuthToken(){
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

		body.add("client_id", clientId);
		body.add("client_secret", clientSecret);
		body.add("grant_type", grantType);

		HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);

		return restTemplate.exchange("https://test.api.amadeus.com/v1/security/oauth2/token", HttpMethod.POST, httpEntity, AuthResponseDto.class)
				.getBody().access_token;
	}
}
