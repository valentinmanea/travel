package com.travel.assistant.facade.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.travel.assistant.proxy.dto.CityDto;

public class FlightDto {
	public long id;
	public double price;
	public String companyName;
	public int seatNumber;
	public LocalDateTime startDate;
	public LocalDateTime endDate;
	public LocalDateTime journeyTime;
	public RatingDto rating;
	public List<UserDto> users;
	public CityDto destinationCity;
	public CityDto startCity;
}
