package com.travel.assistant.proxy.dto.flight;

import java.util.Date;
import java.util.List;

public class SecondLevelFlightDto {
	public String type;
	public String id;
	public String source;
	public boolean instantTicketingRequired;
    public boolean nonHomogeneous;
    public boolean oneWay;
    public Date lastTicketingDate;
    public int numberOfBookableSeats;
    public List<ItinerairesFlightDto> itineraries;
    public PriceFlightDto price;
    public List<TravelerPricingsFlightDto> travelerPricings;
}
