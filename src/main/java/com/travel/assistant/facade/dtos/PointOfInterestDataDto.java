package com.travel.assistant.facade.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PointOfInterestDataDto {
	public String type;
	public String subType;
	public GeoCodeDto geoCode;
	public String name;
	public String category;
	@JsonProperty("tags")
	public List<String> tags;

	public PointOfInterestDataDto() {
	}
}