package com.travel.assistant.proxy.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelFirstLevelDto {
	@JsonProperty("data")
	public List<HotelSecondLevelDto> hotelSecondLevelDto;
}
