package com.travel.assistant.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data

public class Car extends BaseEntity{
	@Column(name= "model")
	private String model;
	
	@Column(name= "spots")
	private int spots;
	
	@Column(name= "seats_number")
	private String color;
	
	@Column(name= "km")
	private String km;
	
	@Column(name= "doors")
	private int doors;
	
	@OneToOne(mappedBy="car")
	@JsonIgnore 
	private CarOffer carOffer;

	@Override
	public String toString() {
		return "Car [model=" + model + ", spots=" + spots + ", color=" + color + ", km=" + km + ", doors=" + doors
				+ "]";
	}
	
}
