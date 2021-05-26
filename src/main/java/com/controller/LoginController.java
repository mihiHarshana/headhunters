package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CV;
import com.model.User;
import com.service.LoginService;

/**
 * 
 *Login Controller
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth/")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("get-user")
	public User getUser(@RequestParam int userId) {
		System.out.print("test");
		User user= null;
		user =	loginService.getUser(userId);
		return user;
		
	}
	
	@PostMapping("login-user")
	public Response getLogin(@RequestBody String json) {
		Response response = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			User user = objectMapper.readValue(json, User.class);
			 user = loginService.login(user.getU_name(), user.getU_password());
			 if (user != null) {
					response = new Response(200, user.getU_id(), "Login operation is sucessfully", user);	
				}else {
					response = new Response(204, 0, "Operation is failed. Please enter valid authentication details.", null);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response = new Response(500, 0, e.getMessage(), null);
			}
			return response;
		
	}

	

	
}
