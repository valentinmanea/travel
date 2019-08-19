package com.travel.assistant.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.travel.assistant.entities.IataCodeAndCityName;
import com.travel.assistant.entities.User;
import com.travel.assistant.repo.HotelOfferRepo;
import com.travel.assistant.repo.IataCodeAndCityNameRepo;
import com.travel.assistant.repo.SearchHistoryRepo;
import com.travel.assistant.repo.UserRepo;

@Service
public class CityNameGeneratorByHistory {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	SearchHistoryRepo searchHistoryRepo;
	
	@Autowired
	IataCodeAndCityNameRepo iataCodeAndCityNameRepo;
	@Autowired
	HotelOfferRepo hotelOfferRepo;
	
	public String generateCityName() {
		int chance;
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if(user != null) {
			List<String> keywords = user.getAllHistories().stream().map(sh -> sh.keyword).collect(Collectors.toList());
			Random random = new Random();
			if(!keywords.isEmpty()) {
				if(keywords.size() < 5) {
					chance = 30;
				}else if(keywords.size() < 20) {
					chance = 40;
				}else chance = 50;
				String selectedKeyword = keywords.get(random.nextInt(keywords.size()-1));
				System.out.println("selected keyword:" + selectedKeyword);
				if(random.nextInt(100) < chance) {
					System.out.println("a fost ales cu sansa: " + chance);
					return selectedKeyword;
				}
			}
		}
		List<IataCodeAndCityName> list= iataCodeAndCityNameRepo.findAll();
		Random random = new Random();
		return list.get(random.nextInt(list.size() - 1)).cityName;
	}
}
