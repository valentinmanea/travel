package com.travel.assistant.facade.dtos;

import java.util.List;

import com.travel.assistant.proxy.dto.CityDto;

public class UserDto{
	public long id;
	public String firtName;
	public String lastName;
	public String username;
	public String email;
	public String password;
	public boolean isActive;
	public List<RatingDto> rating;
	public List<FlightDto> startFlights;
	public List<FlightDto> returnFlights;
	public List<CityDto> cities;
}
