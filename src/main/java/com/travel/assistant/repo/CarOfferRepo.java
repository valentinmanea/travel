package com.travel.assistant.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.travel.assistant.entities.CarOffer;
import com.travel.assistant.proxy.dto.CarOfferSearchDto;

public interface CarOfferRepo extends BaseRepository<CarOffer>{
	
	@Query("SELECT c from CarOffer c where c.startDate<=?1 AND c.endDate>=?2 AND c.city = ?3")
	List<CarOffer> findCarOffersBetween(LocalDate startDate, LocalDate endDate, String selectedCity);

}
