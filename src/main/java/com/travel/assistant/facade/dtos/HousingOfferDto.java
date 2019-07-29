package com.travel.assistant.facade.dtos;

import java.time.LocalDate;
import java.util.List;

public class HousingOfferDto {
	public long id;
	
	public double price;
	
	public HotelDto hotel;
	
	public int numberOfDays;
	
	public DateDto startDate;

	public DateDto endDate;
	
	public List<RoomDto> availableRooms;
}
