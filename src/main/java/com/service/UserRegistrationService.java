package com.service;

import com.model.User;

public interface UserRegistrationService {
	public User getUser (int userId);
	public int registerUser(User user);
	public int updateUser(User user);
}
