package com.dao;

import java.util.List;

import com.dto.JobSeekerQualificationsDTO;
import com.model.CV;

public interface JobSeekerDao  {
	public CV  getCV (int userId);
	public int addCV (CV cv);
	public int updateCV (CV cv);
	public boolean deleteCv(int cv_id);
}
