package com.travel.assistant.proxy.dto;

public class CityDto{
	public int id;
	public  String wikiDataId;
	public String type;
	public String city;
	public String name;
	public String country;
	public String countryCode;
	public String region;
	public String regionCode;
	public String latitude;
	public String longitude;
	@Override
	public String toString() {
		return "CityDto [id=" + id + ", wikiDataId=" + wikiDataId + ", type=" + type + ", city=" + city + ", name="
				+ name + ", country=" + country + ", countryCode=" + countryCode + ", region=" + region
				+ ", regionCode=" + regionCode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
}
