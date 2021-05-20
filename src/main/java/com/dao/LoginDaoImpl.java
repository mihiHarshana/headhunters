package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public User getUser (int userId) {
		User user = null;
        try {
            Session session = sessionFactory.openSession();
            user = (User) session.get(User.class, userId);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
	}

	@Override
	public User login(String userName, String password) {
		User user = null;
		try {
            Session session = sessionFactory.openSession();
            user =  (User )session.createQuery("from User where u_name = '" + userName + "' and u_password = '" + password + "'").uniqueResult();
           
          //  user = (User) session.get(User.class, userName );  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
	}

}
