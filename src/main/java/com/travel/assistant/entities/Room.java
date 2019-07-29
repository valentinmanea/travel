package com.travel.assistant.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.travel.assistant.enums.Currency;

import lombok.Data;

@Entity
@Data
public class Room extends BaseEntity{
	
	private int capacity;
	
	private double price;
	
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id") 
	public Hotel hotel;
	
	@ManyToMany
	private List<HousingReservation> housingReservation;
	
	@ManyToMany
	private List<HousingOffer> housingOffers;
	
	@Override
	public String toString() {
		return "Room [capacity=" + capacity + ", price=" + price + "]";
	}
	public void addHousingOffer(HousingOffer housingOffer) {
		if(this.housingOffers == null) {
			this.housingOffers = new ArrayList<>();
		}
		this.housingOffers.add(housingOffer);
	}
}
