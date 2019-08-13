package com.travel.assistant.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hotel_offer")
public class HotelOffer extends BaseEntity {
	
	@Column(name="rate_code")
	public String rateCode;
	public String adults;
	public String currency;
	public String total;
	@Column(name="hotel_id")
	public String hotelId;
	public String name;
	@ManyToOne
	@JsonIgnore
	public User user;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date createdDate;
}
 