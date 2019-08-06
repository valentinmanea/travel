package com.travel.assistant.proxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Radu-ValentinManea
 *
 */
public class RoomDto{
	 public String type;
	 @JsonProperty("typeEstimated")
     public TypeEstimatedRoomDto typeEstimated;
	 @JsonProperty("description")
     public RoomDescriptionDto roomDescriptionDto;
}
