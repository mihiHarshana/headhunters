package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * CV Model
 */
@Table(name = "cv")
@Entity
public class CV {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cv_id")
	private int cv_id;
	@Column(name="u_id")
	private int u_id ;
	@Column(name="f_name")
	private String f_name;
	@Column(name="l_name")
	private String l_name;
	public int getCv_id() {
		return cv_id;
	}
	public void setCv_id(int cv_id) {
		this.cv_id = cv_id;
	}
	@Column(name="tel_no")
	private String tel_no;
	@Column(name="address")
	private String address;
	@Column(name="emailaddress")
	private String emailaddress;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

}
