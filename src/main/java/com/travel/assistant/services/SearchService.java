package com.travel.assistant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.assistant.entities.SearchHistory;
import com.travel.assistant.entities.SearchHistoryRepo;

@Service
public class SearchService {
	
	@Autowired
	SearchHistoryRepo repo;
	
	public void addToHistory(String searchType, String keyword) {
		SearchHistory searchHistory = new SearchHistory(searchType, keyword);
		repo.save(searchHistory);
	}
}
