package com.assignment.login_signup_microservice.models;

public class AuthenticationRequest {

	private Long contact;
	private String password;

	public AuthenticationRequest() {
	}

	public AuthenticationRequest(Long contact, String password) {
		super();
		this.contact = contact;
		this.password = password;
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
