package com.travel.assistant.repo;

import java.util.Optional;

import com.travel.assistant.entities.Hotel;

public interface HotelRepo extends BaseRepository<Hotel>{

	Optional<Hotel> findFirstByName(String hotelName);
 
}
 