package com.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.CV;

@Repository
public class JobSeekerDaoImpl implements JobSeekerDao {

	@Autowired
	SessionFactory sessionFactory;

	public CV getCV(int userId) {
		Session session = sessionFactory.openSession();
		CV cv = null;
		try {

			cv = (CV) session.get(CV.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cv;
	}

	@Override
	public int addCV(CV cv) {
		Session session = sessionFactory.openSession();
		int id = 0;
		try {
			session.save(cv);
			Serializable serializable = session.getIdentifier(cv);
			id = (int) serializable;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public int updateCV(CV cv) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();

		int id = 0;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(cv);
			Serializable serializable = session.getIdentifier(cv);
			id = (int) serializable;
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public boolean deleteCv(int cv_id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean isDeleted = false;
		try {
			transaction = session.beginTransaction();
			CV cv = (CV) session.get(CV.class, cv_id);
			session.delete(cv);
			Serializable serializable = session.getIdentifier(cv);
			int result = (int) serializable;
			if (result == 0)
				isDeleted = true;
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}


}
