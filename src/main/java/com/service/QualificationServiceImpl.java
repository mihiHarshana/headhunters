package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.QualificationDao;
import com.dto.JobSeekerQualificationsDTO;
import com.model.Qualification;
import com.model.QualificationType;
@Transactional
@Service
public class QualificationServiceImpl implements QualificationService {
	
	@Autowired
	QualificationDao qualificationDao;

	@Override
	@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
	public int addQualification(Qualification qualification) {
		return qualificationDao.addQualification(qualification);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
	public int updateQualification(Qualification qualification) {
		return qualificationDao.updateQualification(qualification);
	}

	@Override
	public Qualification getQualification(int id) {
		return qualificationDao.getQualification(id);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
	public boolean deleteQualification(int id) {
		return qualificationDao.deleteQualification(id);
	}

	@Override
	public List<JobSeekerQualificationsDTO> searchBy(int[] qualificationTypes, String searchValue) {
		return qualificationDao.searchBy(qualificationTypes, searchValue);
	}

	@Override
	public List<QualificationType> getQualificationTypes() {
		return qualificationDao.getQualificationTypes();
	}

	@Override
	public List<Qualification> getQualificationsByUserID(int userId) {
		return qualificationDao.getQualificationsByUserID(userId);
	}

}
