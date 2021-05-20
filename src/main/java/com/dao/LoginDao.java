package com.dao;

import com.model.User;

public interface LoginDao {
	public User getUser (int userId);
	public User login (String userName, String password);

}
