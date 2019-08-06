package com.travel.assistant.facade.dtos;

import com.travel.assistant.proxy.dto.CityDto;

public class DealDto{
	public long id;
	public CityDto startCity;
	public CityDto destinationCity;
	public FlightDto startFlight;
	public FlightDto returnflight;
}

