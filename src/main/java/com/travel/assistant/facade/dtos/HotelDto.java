package com.travel.assistant.facade.dtos;

import java.util.List;

import com.travel.assistant.entities.Room;

public class HotelDto{
	
	public long id;
	
	public String name;
	

	public String city;
	
	public List<RoomDto> rooms;
	
	public String imgSrc;

	@Override
	public String toString() {
		return "HotelDto [name=" + name + ", city=" + city + ", rooms=" + rooms + ", imgSrc=" + imgSrc + "]";
	}
	
	
}
