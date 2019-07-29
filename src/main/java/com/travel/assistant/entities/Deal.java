package com.travel.assistant.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Deal extends BaseEntity{
	
	@Column(name = "start_city")
	private String startCity;
	
	@Column(name = "destination_city")
	private String destinationCity;
	
	@ManyToOne
	@JoinColumn( name = "hotel_id")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "start_flight_id")
	private Flight startFlight;
	
	@ManyToOne
	@JoinColumn(name = "return_flight_id")
	private Flight returnFlight;
}

