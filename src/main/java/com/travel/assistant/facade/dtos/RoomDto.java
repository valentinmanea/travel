package com.travel.assistant.facade.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Radu-ValentinManea
 *
 */
public class RoomDto{
	public long id;
	public int capacity;
	public double price;
	public String currency;
	@JsonIgnore
	public HotelDto hotel;
	@Override
	public String toString() {
		return "RoomDto [id=" + id + ", capacity=" + capacity + ", price=" + price + ", currency=" + currency
				+  "]";
	}

	
	
}
