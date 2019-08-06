package com.travel.assistant.proxy.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelDto {
	 public String type;
	 public String hotelId;
	 public String chainCode;
	 public String dupeId;
	 public String name;
	 public String rating;
	 public String cityCode;
	 public String latitude;
	 public String longitude;
	 @JsonProperty("address")
     public HotelAddressDto hotelAddressDto;
	 @JsonProperty("contact")
     public HotelContactDto hotelContactDto;
	 @JsonProperty("description")
     public HotelDescriptionDto hotelDescription;
     public List<String> amenities;
}
