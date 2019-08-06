package com.travel.assistant.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "client")
public class Client extends BaseEntity {
	
	@Column(name = "first_name")
	private String firtName;
	
	@Column(name = "last_name")
	private String lastName;
	
	
	
	@ManyToMany
	@JoinTable(name = "flight_reservation", 
		        joinColumns = { @JoinColumn(name = "start_flight_id") }, 
		        inverseJoinColumns = {@JoinColumn(name = "client_id") })
	private List<Flight> startFlights;
	
	@ManyToMany
	@JoinTable(name = "flight_reservation", 
	joinColumns = { @JoinColumn(name = "return_flight_id") }, 
	inverseJoinColumns = {@JoinColumn(name = "client_id") })
	private List<Flight> returnFlights;
	
}
