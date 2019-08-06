package com.travel.assistant.proxy.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GeoDbDto {
	@JsonProperty("data")
	public List<CityDto> data;
	
	@JsonProperty("metadata")
	public MetaDataDto metaData;
}
