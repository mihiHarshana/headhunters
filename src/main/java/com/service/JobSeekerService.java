package com.service;

import com.model.CV;
import com.model.User;

public interface JobSeekerService {
	public CV  getCV (int userId);
	public int addCV (CV cv);
	public int updateCV (CV cv);
	public boolean deleteCv(int cv_id);
}
