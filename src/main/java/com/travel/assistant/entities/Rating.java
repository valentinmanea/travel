package com.travel.assistant.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Rating extends BaseEntity{
	
	private double value;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client; 
	
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	@OneToOne(mappedBy = "rating")
	private Flight flight;
}
