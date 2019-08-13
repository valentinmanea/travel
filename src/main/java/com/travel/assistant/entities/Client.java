package com.travel.assistant.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends BaseEntity {
	
	@Column(name = "first_name")
	private String firstName;
	
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

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	
}
