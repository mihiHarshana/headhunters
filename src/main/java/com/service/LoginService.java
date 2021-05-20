package com.service;

import com.model.User;

public interface LoginService {
	public User getUser (int userId);
	public User login(String username, String password);

}
