package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.JobSeekerDaoImpl;
import com.dto.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CV;
import com.model.User;
import com.service.JobSeekerService;
import com.service.LoginService;
import com.service.UserRegistrationService;

/**
 * 
 * Login Controller
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user/")
public class UserRegistrationController {

	@Autowired
	UserRegistrationService userRegistrationService;
	
	@Autowired
	JobSeekerService jobSeekerService;

	@GetMapping("get-user")
	public User getUser(@RequestParam int userId) {
		User user = null;
		try {
			user = userRegistrationService.getUser(userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	@PostMapping("register")
	public Response registerUser(@RequestBody String userJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {
			User user = objectMapper.readValue(userJson, User.class);
			int userId = userRegistrationService.registerUser(user);
			if (userId != 0) {
				CV cv = new CV();
				cv.setU_id(userId);
				int cvId = this.jobSeekerService.addCV(cv);
				response = new Response(200, userId, "User Registerd sucessfully", null);
			} else {
				response = new Response(204, 0, "Operation is failed", null);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(500, 0, e.getMessage(), null);
		}
		return response;
	}
	
	@PutMapping("update-user")
	public Response updateUser(@RequestBody String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {
			User user = objectMapper.readValue(json, User.class);
			int userId = userRegistrationService.updateUser(user);
			if (userId != 0) {
				response = new Response(200, userId, "User updated sucessfully", null);
			} else {
				response = new Response(204, 0, "Operation is failed", null);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(500, 0, e.getMessage(), null);
		}
		return response;
	}
}
