package com.travel.assistant.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FlightOffer extends BaseEntity {
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_ticketind_date")
	public Date lastTicketingDate;
	public String price;
	public String source;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	public Date createdDate;
	
	@ManyToOne
	@JsonIgnore
	public User user;
	
	@OneToOne
	public FullOffer fullOffer;
	
}
