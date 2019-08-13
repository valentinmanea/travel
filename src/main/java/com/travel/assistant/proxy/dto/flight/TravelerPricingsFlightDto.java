package com.travel.assistant.proxy.dto.flight;

import java.util.List;

public class TravelerPricingsFlightDto {
      public String travelerId;
      public String fareOption;
      public String travelerType;
      public PriceFlightDto price;
      public List<FareDetailsBySegmentFlightDto> fareDetailsBySegment;
}
