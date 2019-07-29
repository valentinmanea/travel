package com.travel.assistant.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Flight extends BaseEntity {
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "seat_nr")
	private int seatNumber;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "journey_time")
	private LocalDateTime journeyTime;
	
	@OneToOne
	@JoinColumn(name = "flight_id")
	private Rating rating;
	
	@ManyToMany
	private List<Client> clients;
	
	@Column(name = "destination_city")
	private String destinationCity;
	
	@Column(name = "start_city")
	private String startCity;
}
