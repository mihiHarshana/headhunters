package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * User Model
 */
@Table(name = "user")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="u_id")
	private int u_id ;
	@Column(name="u_name")
	private String u_name;
	@Column(name="u_password")
	private String u_password;
	@Column(name="u_type")
	private String u_type;
	
	
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_type() {
		return u_type;
	}
	public void setU_type(String u_type) {
		this.u_type = u_type;
	}
	
	

}
