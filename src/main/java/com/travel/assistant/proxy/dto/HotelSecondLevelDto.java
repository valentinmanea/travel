package com.travel.assistant.proxy.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelSecondLevelDto {
	public String type;
	@JsonProperty("hotel")
	public HotelDto hotelDto;
	public boolean available;
	@JsonProperty("offers")
	List<HotelOfferDto> offers;
}
