package com.travel.assistant.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hotel_offer")
public class HotelOffer extends BaseEntity {
	public String id;
	public String rateCode;
	@OneToOne
    public Room room;
	public String adults;
	
	public String currency;
	public String base;
	public String total;
}
 