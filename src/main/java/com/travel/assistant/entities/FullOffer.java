package com.travel.assistant.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class FullOffer extends BaseEntity{
		
	@OneToOne
	@JoinColumn(name="flight_offer_id")
	private FlightOffer flightOffer;
	
	@OneToOne
	@JoinColumn(name="hotel_offer_id")
	private HotelOffer hotelOffer;
	
}
