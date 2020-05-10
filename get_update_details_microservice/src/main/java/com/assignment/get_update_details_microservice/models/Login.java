package com.assignment.get_update_details_microservice.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Login implements Serializable {

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

	@Override
	public String toString() {
		return "Login [loginid=" + loginid + ", contact=" + contact + ", password=" + password + "]";
	}

}
