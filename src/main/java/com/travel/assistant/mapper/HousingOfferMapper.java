package com.travel.assistant.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.travel.assistant.controllers.DateService;
import com.travel.assistant.entities.HousingOffer;
import com.travel.assistant.facade.dtos.HotelDto;
import com.travel.assistant.facade.dtos.HousingOfferDto;
import com.travel.assistant.repo.HotelRepo;

@Component
public class HousingOfferMapper {
	
	@Autowired
	HotelRepo hotelRepo;
	
	@Autowired
	DateService service;
	
	@Autowired
	RoomMapper roomMapper;
	
	@Autowired
	HotelMapper hotelMapper;
	
	public HousingOffer toEntity(HousingOfferDto dto) {
		HousingOffer offer = new HousingOffer();
		offer.setStartDate(service.toLocalDate(dto.startDate));
		offer.setEndDate(service.toLocalDate(dto.endDate));
		return offer;
	}
	
	public HousingOfferDto toDto(HousingOffer entity) {
		HousingOfferDto dto = new HousingOfferDto();
		dto.id = entity.getId();
		dto.startDate = service.toDateDto(entity.getStartDate());
		dto.endDate = service.toDateDto(entity.getEndDate());
		dto.availableRooms = entity.getAvailableRooms().stream().map(roomMapper::toDto).collect(Collectors.toList());
		dto.hotel = (entity.getHotel() != null) ? hotelMapper.toDto(entity.getHotel()) : new HotelDto();
		return dto;
	}
}