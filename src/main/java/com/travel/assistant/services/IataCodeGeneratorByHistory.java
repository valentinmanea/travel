package com.travel.assistant.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.travel.assistant.entities.IataCodeAndCityName;
import com.travel.assistant.entities.User;
import com.travel.assistant.repo.IataCodeAndCityNameRepo;
import com.travel.assistant.repo.SearchHistoryRepo;
import com.travel.assistant.repo.UserRepo;

@Service
public class IataCodeGeneratorByHistory {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	SearchHistoryRepo searchHistoryRepo;
	
	@Autowired
	IataCodeAndCityNameRepo iataCodeAndCityNameRepo;
	
	public String generateIataCode() {
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if(user != null) {
			Optional<String> keywordOptional = searchHistoryRepo.findAll().stream().map(sh -> sh.keyword).findAny();
			if(keywordOptional.isPresent()) {
				return keywordOptional.get();
			}
		}
		List<IataCodeAndCityName> list= iataCodeAndCityNameRepo.findAll();
		Random random = new Random();
		return list.get(random.nextInt(list.size() - 1)).iataCode;
	}

}
