package com.travel.assistant.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class SearchHistory extends BaseEntity{
	@Column(name = "search_type")
	public String searchType;
	
	@Column(name = "keyword")
	public String keyword;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	public Date searchDate;

	public SearchHistory(String searchType, String keyword) {
		super();
		this.searchType = searchType;
		this.keyword = keyword;
	}

	public SearchHistory() {
		super();
	}
	
}
