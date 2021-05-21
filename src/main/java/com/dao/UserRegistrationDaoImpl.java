package com.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public class UserRegistrationDaoImpl implements UserRegistrationDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public User getUser (int userId) {
		Session session = sessionFactory.openSession();
		User user = null;
        try {
            
            user = (User) session.get(User.class, userId);  
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			session.close();
		}
        return user;
	}

	
	//TODO equals
	@Override
	public int registerUser(User user) {
		Session session = sessionFactory.openSession();
		int userId = 0;
		try {
			 session.save(user);
			 Serializable serializable = session.getIdentifier(user);
	         userId = (int) serializable;
		}catch (Exception e) {
			 e.printStackTrace();
		}finally {
			session.close();
		}
		return userId;
	}
}
