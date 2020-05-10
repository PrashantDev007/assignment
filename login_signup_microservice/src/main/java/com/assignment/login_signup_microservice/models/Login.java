package com.assignment.login_signup_microservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Login {

	@Id
	private String loginid;

	private Long contact;

	private String password;

	public Login() {
	}

	public String getLoginId() {
		return loginid;
	}

	public void setLoginId(String loginId) {
		this.loginid = loginId;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
