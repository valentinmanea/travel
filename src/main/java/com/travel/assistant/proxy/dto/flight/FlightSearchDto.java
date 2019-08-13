package com.travel.assistant.proxy.dto.flight;

import com.travel.assistant.facade.dtos.DateDto;

public class FlightSearchDto {
	public String startCity;
	public String destinationCity;
	public DateDto startDate;
	public DateDto endDate;
	public int adults;
}
