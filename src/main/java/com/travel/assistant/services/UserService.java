package com.travel.assistant.services;


import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.assistant.entities.Role;
import com.travel.assistant.entities.User;
import com.travel.assistant.entities.VerificationToken;
import com.travel.assistant.facade.dtos.UserDto;
import com.travel.assistant.repo.RoleRepo;
import com.travel.assistant.repo.TokenRepository;
import com.travel.assistant.repo.UserRepo;

@Service
public class UserService{
    @Autowired
	private UserRepo userRepo;
    
    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired 
    private TokenRepository tokenRepository;
    
    public User save(UserDto newUserDto) {
    	User user = new User();
    	Role role;
    	mapper.map(newUserDto, user);
    	Optional<Role> roleOptional = roleRepo.findFirstByName("USER");
    	if(!roleOptional.isPresent()) {
    		role = new Role("USER"); 
    		roleRepo.save(role);
    	}else {
    		role = roleOptional.get();
    	}
		user.setRole(role);
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    private boolean emailExist(String email) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
     
    public User getUser(String verificationToken) {
    	Optional<VerificationToken> verificationTokenOptional = tokenRepository.findByToken(verificationToken);
        User user = verificationTokenOptional.get().getUser();
        return user;
    }
     
    public Optional<VerificationToken> getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
     
    public void saveRegisteredUser(User user) {
        userRepo.save(user);
    }
     
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        myToken.setExpiryDate(LocalDateTime.now().plusMinutes(10L));
        tokenRepository.save(myToken);
    }
    
}