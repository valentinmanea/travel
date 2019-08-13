package com.travel.assistant.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.assistant.enums.Currency;

import lombok.Data;

@Entity
@Data
public class CarOffer extends BaseEntity{
	
	@JsonProperty("startDate")
	private LocalDate startDate;

	@JsonProperty("endDate")
	private LocalDate endDate;
	
	private String city;
	
	private double price;
	
	private Currency currency;
	
	@OneToOne()
	private Car car;
	
	@ManyToOne
	@JsonIgnore
	private User rentalUser;
	
	@Override
	public String toString() {
		return "CarOffer [startOffer=" + startDate + ", endOffer=" + endDate + ", city=" + city + ", price=" + price
				+ ", currency=" + currency + ", car=" + car + "]";
	}
	
}
