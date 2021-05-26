package com.dto;

public class SearchQualificationRequest {
	public String searchValue;
	public int[] qualificationTypes;
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public int[] getQualificationTypes() {
		return qualificationTypes;
	}
	public void setQualificationTypes(int[] qualificationTypes) {
		this.qualificationTypes = qualificationTypes;
	}
	
}
