package com.travel.assistant.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.travel.assistant.entities.Hotel;
import com.travel.assistant.facade.dtos.HotelDto;

@Component
public class HotelMapper {
	@Autowired
	RoomMapper roomMapper;
	
	public Hotel toEntity(HotelDto dto) {
		Hotel hotel = new Hotel();
		hotel.setId(dto.id);
		hotel.setCity(dto.city);
		hotel.setImgSrc(dto.imgSrc);
		hotel.setName(dto.name);
		return hotel;
	}

	public HotelDto toDto(Hotel hotel) {
		HotelDto hotelDto = new HotelDto();
		hotelDto.id = hotel.getId();
		hotelDto.city = hotel.getCity();
		hotelDto.imgSrc = hotel.getImgSrc();
		hotelDto.name = hotel.getName();
		hotelDto.rooms = hotel.getRooms().stream().map(roomMapper::toDto).collect(Collectors.toList());
		
		return hotelDto;
	}
}
