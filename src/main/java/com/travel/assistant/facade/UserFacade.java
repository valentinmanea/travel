package com.travel.assistant.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.assistant.entities.User;
import com.travel.assistant.repo.UserRepo;

@Service
public class UserFacade {
	
	@Autowired
	private UserRepo userRepo;
	
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public void addUser(User user) {
		userRepo.save(user);
	}

	public void editUser(User user) {
		userRepo.save(user);
	}
	public void deleteById(long id) {
		userRepo.deleteById(id);
	}

	public User getUserById(long id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}else throw new IllegalArgumentException("User can't be found");
	}

}
