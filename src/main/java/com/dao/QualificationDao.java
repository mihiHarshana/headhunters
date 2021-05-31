package com.dao;

import java.util.List;

import com.dto.JobSeekerQualificationsDTO;
import com.model.Qualification;
import com.model.QualificationType;

public interface QualificationDao {

	public int addQualification(Qualification qualification);
	
	public int updateQualification(Qualification qualification);
	
	public Qualification getQualification(int id);
	
	public boolean deleteQualification(int id);
	
	public List<JobSeekerQualificationsDTO> searchBy(int[] qualificationTypes, String searchValue);
	
	public List<QualificationType> getQualificationTypes();
	
	public List<Qualification> getQualificationsByUserID(int userId);
}
