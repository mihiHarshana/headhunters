package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.LoginDao;
import com.dao.UserRegistrationDao;
import com.model.User;

@Service @Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService{
	
	@Autowired
	UserRegistrationDao userRegistrationDao;

	public User getUser(int userId) {
		return userRegistrationDao.getUser(userId);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
	public int registerUser(User user) {
		// TODO Auto-generated method stub
		return userRegistrationDao.registerUser(user);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
	public int updateUser(User user) {
		return userRegistrationDao.updateUser(user);
	}




}
