package com.travel.assistant.proxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelOfferDto {
	public String id;
	public String rateCode;
	@JsonProperty("room")
    public RoomDto roomDto;
	@JsonProperty("guests")
    public HotelGuestsDto hotelGuestDto;
	@JsonProperty("price")
    public HotelPriceDto hotelPriceDto;
}
