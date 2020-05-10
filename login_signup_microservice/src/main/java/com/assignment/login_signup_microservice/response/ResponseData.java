package com.assignment.login_signup_microservice.response;

import org.springframework.stereotype.Component;

public class ResponseData {

	private String code;
	private String message;
	private String data;

	public ResponseData() {
	}

	public ResponseData(String code, String message, String data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
