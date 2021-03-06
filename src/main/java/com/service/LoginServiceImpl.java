package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.LoginDao;
import com.model.User;

@Service @Transactional
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;

	public User getUser(int userId) {
		return loginDao.getUser(userId);
	}


	public User login(String userName, String password) {
	
		return  loginDao.login(userName, password);
	}

}
