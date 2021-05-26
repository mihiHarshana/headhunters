package com.dao;

import com.model.User;

public interface UserRegistrationDao {
	public User getUser (int userId);
	public int registerUser (User user);
	public int updateUser(User user);
}
