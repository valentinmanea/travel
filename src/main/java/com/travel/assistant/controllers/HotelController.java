package com.travel.assistant.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.entities.Hotel;
import com.travel.assistant.entities.Room;
import com.travel.assistant.enums.Currency;
import com.travel.assistant.facade.dtos.HotelDto;
import com.travel.assistant.facade.dtos.InfoDto;
import com.travel.assistant.facade.dtos.RoomDto;
import com.travel.assistant.mapper.HotelMapper;
import com.travel.assistant.mapper.RoomMapper;
import com.travel.assistant.repo.HotelRepo;
import com.travel.assistant.repo.RoomRepo;
import com.travel.assistant.services.HotelService;


@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HotelController {
	
	@Autowired
	private HotelRepo repo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	HotelMapper hotelMapper;
	
	@Autowired
	RoomMapper roomMapper;
	
	@Autowired 
	RoomRepo roomRepo;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	DateService dateService;
	
	@PostMapping("/add")
	public void addHotel(@RequestBody HotelDto hotelDto) {
		List<Room> rooms = hotelDto.rooms.stream()
				.map(roomMapper::toEntity)
				.collect(Collectors.toList());
		Hotel hotel = hotelMapper.toEntity(hotelDto);
		repo.save(hotel);
		rooms.forEach(room->room.setHotel(hotel));
		roomRepo.saveAll(rooms);
	}
	
	@GetMapping("/all")
	public List<HotelDto> getAllHotels(){
		return 
				repo.findAll()
				.stream()
				.map(hotelMapper::toDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public HotelDto getHotelById(@PathVariable("id") long id) {
		System.out.println("aici");
		Optional<Hotel> optionalHotel = repo.findById(id);
		if(optionalHotel.isPresent()) {
			return hotelMapper.toDto(optionalHotel.get()); 
		}else
			throw new IllegalArgumentException("Hotel with id: "+id+"can't be found");
	}
	
	@PutMapping("/edit")
	public void updateHotel(@RequestBody HotelDto dto) {
		if(dto.id != 0) {
			Hotel hotel = repo.findById(dto.id).get();
			List<Room> rooms = dto.rooms.stream().map(item->roomMapper.toEntity(item)).collect(Collectors.toList());
			roomRepo.saveAll(rooms);
			rooms.forEach(room->room.setHotel(hotel));
			hotel.addRooms(rooms);
			repo.save(hotel);
		}else
			throw new IllegalArgumentException("Hotel with id: "+dto.id+"can't be found");
	}
	
	@DeleteMapping("/{id}")
	public void deleteHotelById(@PathVariable("id") long id) {
		Optional<Hotel> optionalHotel = repo.findById(id);
		if(optionalHotel.isPresent()) {
			repo.deleteById(id);
		}else
			throw new IllegalArgumentException("Hotel with id: "+id+"can't be found");
	}
	
	@DeleteMapping("/room/{id}")
	public void deleteRoomById(@PathVariable("id") long id) {
		Optional<Room> optionalRoom = roomRepo.findById(id);
		if(optionalRoom.isPresent()) {
			roomRepo.deleteById(id);
		}else
			throw new IllegalArgumentException("Hotel with id: "+id+"can't be found");
	}
	
	@GetMapping("/reserved-dates")
	public List<LocalDate> retrieveDates(@RequestParam("hotelName") String hotelName){
		Hotel hotel = repo.findFirstByName(hotelName).get();
		return hotel.retrieveAllReservedDates();
	}
	@GetMapping("/names")
	public List<String> getAllHotelNames(){
		return 
				repo.findAll()
				.stream()
				.map(Hotel::getName)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/available-rooms")
	public List<RoomDto> getAvailableRooms(@RequestBody InfoDto dto){
		Hotel hotel = repo.findFirstByName(dto.hotelName).get();
		System.out.println("start: " + dto.startDate);
		LocalDate startDate = dateService.toLocalDate(dto.startDate);
		LocalDate endDate =  dateService.toLocalDate(dto.endDate);

		return hotelService.getAvailableRooms(hotel, startDate, endDate)
				.stream().map(roomMapper::toDto).collect(Collectors.toList());
	}
	@GetMapping("/currencies")
	public List<String> getCurrencies(){
		return 
				Arrays.asList(Currency.values()).stream().map(Currency::getSymbol).collect(Collectors.toList());
	}
}
