package com.travel.assistant.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Car extends BaseEntity{
	@Column(name= "model")
	public String model;
	
	@Column(name= "seats_number")
	public int seatsNumber;
}
