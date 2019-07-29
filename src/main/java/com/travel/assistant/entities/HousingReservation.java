package com.travel.assistant.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="housing_reservation")
public class HousingReservation extends BaseEntity{
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;

	@ManyToOne
	private Hotel hotel;
	
	@ManyToMany
	@JoinTable(name="Housing_Reservation_Room")
	private List<Room> rooms;
	
	@ManyToOne
	private Client client;
	
}
