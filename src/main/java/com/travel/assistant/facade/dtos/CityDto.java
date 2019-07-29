package com.travel.assistant.facade.dtos;

import java.util.List;

public class CityDto{
	public long id;
	public String name;
	public String img;
	public String keywords;
	public String country;
	public List<HotelDto> hotels;
	public FlightDto flight;
	public List<UserDto> users;
}
