package com.travel.assistant.controllers;

import static java.util.stream.Collectors.toList;

import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.entities.User;
import com.travel.assistant.facade.UserFacade;
import com.travel.assistant.facade.dtos.UserDto;
import com.travel.assistant.services.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class UserController {

	@Autowired
	private UserFacade userFacade;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private UserService userService;


	@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<UserDto> findAllUsers() {
		List<UserDto> usersDto = userFacade.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(toList());
		usersDto.forEach(System.out::println);
		return usersDto;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
	public User findUserById(@PathVariable long id) {
		return userFacade.getUserById(id);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/user")
	public void addUser(@RequestBody UserDto userDto) {
		userService.save(userDto);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/user")
	public void editUser(@RequestBody User user) {
		userFacade.editUser(user);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/user/{id}")
	public void deleteUserById(@PathVariable long id) {
		userFacade.deleteById(id);
	}
}
