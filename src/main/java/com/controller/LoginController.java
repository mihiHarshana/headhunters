package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.LoginService;

/**
 * 
 *Login Controller
 */

@RestController
@RequestMapping("/api/")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("user")
	public User getUser(@RequestParam int userId) {
		System.out.print("test");
		User user= null;
		user =	loginService.getUser(userId);
		return user;
		
	}
	
	@GetMapping("data-get")
	public String getData() {
		return "Hello";
		
	}
	
	@GetMapping("login")
	public User getLogin(@RequestParam String userName, String password) {
		
		User user = null;
		user = loginService.login(userName, password);
		return user;
		
	}

	

	
}
