package com.travel.assistant.proxy.dto.flight;

public class SegmentsFlightDto {
        public DepartureFlightDto departure;
        public ArrivalFlightDto arrival;
        public String carrierCode;
        public int number;
        public int numberOfStops;
        public boolean blacklistedInEU;
}
