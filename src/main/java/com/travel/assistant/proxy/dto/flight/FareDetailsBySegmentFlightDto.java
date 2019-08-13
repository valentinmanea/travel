package com.travel.assistant.proxy.dto.flight;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FareDetailsBySegmentFlightDto {
       public String segmentId;
       public String cabin;
       public String fareBasis;
       @JsonProperty("class")
       public String clas;
       public IncludedCheckedBagsFlightDto includedCheckedBags;
}
