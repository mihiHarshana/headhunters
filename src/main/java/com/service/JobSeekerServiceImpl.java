package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.JobSeekerDao;
import com.model.CV;

@Service @Transactional
public class JobSeekerServiceImpl implements JobSeekerService{
	
	@Autowired
	JobSeekerDao jobSeekerDao;

	@Override
	public CV getCV(int userId) {
		return jobSeekerDao.getCV(userId);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
	public int addCV(CV cv) {
		return jobSeekerDao.addCV(cv);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
	public int updateCV(CV cv) {
		return jobSeekerDao.updateCV(cv);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
	public boolean deleteCv(int cv_id) {
		return jobSeekerDao.deleteCv(cv_id);
	}
}
