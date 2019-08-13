package com.travel.assistant.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Iata_City")
public class IataCodeAndCityName extends BaseEntity{
 
	public IataCodeAndCityName(String iataCode, String cityName) {
		super();
		this.iataCode = iataCode;
		this.cityName = cityName; 
	}
 
	public IataCodeAndCityName() {
		super();
	}

	public String iataCode;
	public String cityName;
}
