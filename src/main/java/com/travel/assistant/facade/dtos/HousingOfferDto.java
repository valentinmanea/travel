package com.travel.assistant.facade.dtos;

import java.time.LocalDate;
import java.util.List;

import com.travel.assistant.proxy.dto.RoomDto;

public class HousingOfferDto {
	public long id;
	
	public double price;
	
	
	public int numberOfDays;
	
	public DateDto startDate;

	public DateDto endDate;
	
	public List<RoomDto> availableRooms;
}
