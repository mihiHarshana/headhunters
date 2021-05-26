package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.JobSeekerQualificationsDTO;
import com.model.CV;
import com.model.Qualification;
import com.model.QualificationType;

@Repository
public class QualificationDaoImpl implements QualificationDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int addQualification(Qualification qualification) {
		Session session = sessionFactory.openSession();
		int id = 0;
		try {
			session.save(qualification);
			Serializable serializable = session.getIdentifier(qualification);
			id = (int) serializable;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public int updateQualification(Qualification qualification) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();

		int id = 0;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(qualification);
			Serializable serializable = session.getIdentifier(qualification);
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
	public Qualification getQualification(int id) {
		Session session = sessionFactory.openSession();
		Qualification qualification = null;
		try {
			qualification = (Qualification) session.get(Qualification.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return qualification;
	}

	@Override
	public boolean deleteQualification(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean isDeleted = false;
		try {
			transaction = session.beginTransaction();
			int result= session.createQuery("delete from Qualification where id = " + id).executeUpdate();
			if (result == 1)
				isDeleted = true;
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public List<JobSeekerQualificationsDTO> searchBy(int[] qualificationTypes, String searchValue) {
		Session session = sessionFactory.openSession();
		List<JobSeekerQualificationsDTO> jobSeekerQualificationList = new ArrayList<>();
		try {
			String sql = "SELECT a.u_id, a.f_name, a.l_name, a.emailaddress, a.tel_no, a.address" + " FROM cv as a" + " INNER JOIN qualification as b "
					+ " ON a.u_id = b.u_id where b.value like '%" + searchValue + "%'";

			for (int i = 0; i < qualificationTypes.length; i++) {
				sql += " or b.q_type=" + qualificationTypes[i];
			}

			sql += " GROUP BY a.u_id ";
			SQLQuery query = session.createSQLQuery(sql);
			List results = query.list();
			for (int i = 0; i < results.size(); i++) {
				JobSeekerQualificationsDTO dto = new JobSeekerQualificationsDTO();
				Object[] arr = (Object[]) results.get(i);
				dto.setUserId((int) arr[0]);
				dto.setUserFullName(arr[1].toString() + ' ' + arr[2].toString());
				dto.setEmailAddress(arr[3].toString());
				dto.setTelNo(arr[4].toString());
				dto.setUserAddress(arr[5].toString());
				jobSeekerQualificationList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return jobSeekerQualificationList;
	}

	@Override
	public List<QualificationType> getQualificationTypes() {
		Session session = sessionFactory.openSession();
		List<QualificationType> qualificationTypes =  new ArrayList<>();
		try {
			qualificationTypes = session.createQuery("from QualificationType").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return qualificationTypes;
	}

	@Override
	public List<Qualification> getQualificationsByUserID(int userId) {
		Session session = sessionFactory.openSession();
		List<Qualification> qualifications =  new ArrayList<>();
		try {
			qualifications = session.createQuery("from Qualification where u_id=" + userId).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return qualifications;
	}

}
