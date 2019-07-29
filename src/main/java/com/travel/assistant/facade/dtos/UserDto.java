package com.travel.assistant.facade.dtos;

import java.util.List;

public class UserDto{
	public long id;
	public String firtName;
	public String lastName;
	public String username;
	public String email;
	public String password;
	public boolean isActive;
	public List<RatingDto> rating;
	public List<HotelDto> hotels;
	public List<FlightDto> startFlights;
	public List<FlightDto> returnFlights;
	public List<CityDto> cities;
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firtName=" + firtName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", isActive=" + isActive + ", rating=" + rating + ", hotels=" + hotels
				+ ", startFlights=" + startFlights + ", returnFlights=" + returnFlights + ", cities=" + cities + "]";
	}
	
}
