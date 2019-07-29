package com.travel.assistant.repo;

import com.travel.assistant.entities.User;

public interface UserRepo extends BaseRepository<User> {
	 public User findByUsername(String username);
	 
	 public User findByEmail(String email);
}
