package com.travel.assistant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.travel.assistant.entities.User;


/**
 * This Service class for providing the user credentials from the database.
 * 
 * @author kamal berriga
 *
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
 
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = userService.findByUsername(username);
			if(user != null && user.isDisabled()) {
				throw new RuntimeException("User disabled");
			}
			return user;
	}
}
