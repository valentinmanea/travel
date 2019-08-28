package com.travel.assistant.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.travel.assistant.entities.User;
import com.travel.assistant.entities.VerificationToken;
import com.travel.assistant.facade.dtos.UserDto;
import com.travel.assistant.registrationevent.OnRegistrationCompleteEvent;
import com.travel.assistant.services.UserService;
import com.travel.assistant.util.CustomErrorType;
/** 
 * @author kamal berriga
 *
 */
@RestController
@RequestMapping("auth")
public class AuthController {

	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private MessageSource messages;
  

	// request method to create a new account by a guest
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserDto newUserDto, WebRequest request) {
		System.out.println("request: "+request+"request locale"+request.getLocale()+"request context path" + request.getContextPath());
		if (userService.findByUsername(newUserDto.username) != null) {
			return new ResponseEntity(new CustomErrorType("user with username " + newUserDto.username + "already exist "),
					HttpStatus.CONFLICT);
		}
		User user = userService.save(newUserDto);
		try {
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,
					request.getLocale(), request.getContextPath())); 
		}catch(Exception e) {
			logger.error("e"+e);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) { 
		logger.info("user logged "+principal);
		
		return principal;
	}

	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(@RequestParam("token") String token) {
		String messageValue = "Success";
	    Optional<VerificationToken> verificationTokenOptional = userService.getVerificationToken(token);
	    
	    if (!verificationTokenOptional.isPresent()) {
	       messageValue = "Invalid";
	    }else {
	    	User user = verificationTokenOptional.get().getUser();
	    	if (verificationTokenOptional.get().getExpiryDate().isBefore(LocalDateTime.now())) {
	    		messageValue = "Expired";
	    	} 
	    	if(user.isEnabled()) {
	    		messageValue = "Already confirmed";
	    	}
	    	user.setEnabled(true); 
	    	userService.saveRegisteredUser(user); 
	    }
	     
	    return messageValue;
	}

}
