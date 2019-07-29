package com.travel.assistant.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.entities.HousingOffer;
import com.travel.assistant.entities.Room;
import com.travel.assistant.facade.dtos.HousingOfferDto;
import com.travel.assistant.mapper.HousingOfferMapper;
import com.travel.assistant.mapper.RoomMapper;
import com.travel.assistant.repo.HotelRepo;
import com.travel.assistant.repo.HousingOfferRepo;
import com.travel.assistant.repo.RoomRepo;

@RestController
@RequestMapping("/housing-offer")
@CrossOrigin
public class HousingOfferController {
	@Autowired
	HousingOfferRepo housingOfferRepo;
	
	@Autowired
	RoomRepo roomRepo;
	
	@Autowired
	HotelRepo hotelRepo;
	
	
	@Autowired
	HousingOfferMapper mapper;
	
	@Autowired
	RoomMapper roomMapper;
	
		
	@PostMapping("/add")
	public void addHousingOffer(@RequestBody HousingOfferDto dto) {
		HousingOffer offer = mapper.toEntity(dto);
		
		offer.setHotel(hotelRepo.findFirstByName(dto.hotel.name).get());
		
		List<Room> rooms = dto.availableRooms.stream()
				.map(roomDto -> {
					Room room =roomRepo.findById(roomDto.id).get();
					room.addHousingOffer(offer);
					return room;
				})
				.collect(Collectors.toList());
		offer.setAvailableRooms(rooms);
		housingOfferRepo.save(offer);
	}

	@GetMapping("/all")
	public List<HousingOfferDto> getAllHousingOffers() {
		return housingOfferRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}
}
