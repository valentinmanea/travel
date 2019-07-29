package com.travel.assistant.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.assistant.controllers.DateService;
import com.travel.assistant.entities.Hotel;
import com.travel.assistant.entities.Room;

@Service
public class HotelService {

	@Autowired
	public DateService dateService;
	
	public List<Room> getAvailableRooms(Hotel hotel, LocalDate startDate, LocalDate endDate){
		List<LocalDate> localDates = dateService.getDatesBetween(startDate, endDate);
		return hotel.getRooms().stream().filter(room->{
			List<LocalDate> roomReservedDates= new ArrayList<>();
			room.getHousingReservation()
			.stream()
			.forEach(r->roomReservedDates.addAll(dateService.getDatesBetween(r.getStartDate(), r.getEndDate())));
			return Collections.disjoint(localDates, roomReservedDates);
		}).collect(Collectors.toList());
	}
}
