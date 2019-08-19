package com.travel.assistant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.travel.assistant.entities.SearchHistory;
import com.travel.assistant.entities.User;
import com.travel.assistant.repo.SearchHistoryRepo;
import com.travel.assistant.repo.UserRepo;

@Service
public class SearchService {
	
	@Autowired
	SearchHistoryRepo repo;
	
	@Autowired
	UserRepo userRepo;
	
	public void addToHistory(String searchType, String keyword) {
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if(user != null) {
			SearchHistory searchHistory = new SearchHistory(searchType, keyword);
			searchHistory.user = user;
			repo.save(searchHistory);
			user.addHistory(searchHistory);
		}
	}
}
