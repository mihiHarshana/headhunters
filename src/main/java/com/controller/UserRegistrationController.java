package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.service.LoginService;
import com.service.UserRegistrationService;

/**
 * 
 *Login Controller
 */

@RestController
@RequestMapping("/regi/")
public class UserRegistrationController {
	
	@Autowired
	UserRegistrationService userRegistrationService;
	
	@GetMapping("user")
	public User getUser(@RequestParam int userId) {
		User user= null;
		user =	userRegistrationService.getUser(userId);
		return user;
	}
	
	@PostMapping("register")
	public Response registerUser (@RequestBody String userJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {
			User user = objectMapper.readValue(userJson, User.class);
			 int userId =  userRegistrationService.registerUser(user);
			 response =  new Response(200, userId, "Success", "Successfully created new user");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response =  new Response(504, 0, "Error", e.getMessage());
		}	
		return response;
	}
}