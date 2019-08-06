package com.travel.assistant.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Room extends BaseEntity{
	
	public String type;
	public String category;
	public String beds;
	public String bedType;
	@Column(name = "description_language")
	public String lang;
	@Column(name = "description")
	public String text;
}
