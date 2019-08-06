package com.travel.assistant.mapper;

import org.springframework.stereotype.Component;

import com.travel.assistant.entities.Room;
import com.travel.assistant.enums.Currency;
import com.travel.assistant.proxy.dto.RoomDto;

@Component
public class RoomMapper {
//	
//	public Room toEntity(RoomDto dto){
//		Room room = new Room();
//		room.setId(dto.id);
//		room.setCapacity(dto.capacity);
//		room.setPrice(dto.price);
//		room.setCurrency(Currency.findByKey(dto.currency));
//		return room;
//	}
//	
//	public RoomDto toDto(Room room){
//		RoomDto dto = new RoomDto();
//		dto.id = room.getId();
//		dto.capacity = room.getCapacity();
//		dto.price = room.getPrice();
//		dto.currency = (room.getCurrency() != null)? room.getCurrency().getSymbol(): "";
//		System.out.println("Am mapat currency: " + dto.currency);
//		return dto;
//	}
}
