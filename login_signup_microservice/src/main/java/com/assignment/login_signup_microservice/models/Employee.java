package com.assignment.login_signup_microservice.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "employeetable")
@Entity
public class Employee {

	@Id
	private String empId;

	private String empName;

	@Column(unique = true)
	private Long empContact;

	private String empEmail;

	private String empPhoto;

	@OneToMany
	private List<Skillset> skillSet = new ArrayList<>();

	static long id = 1;

	public Employee() {

		this.empId = "emp" + (id++);

	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Long getEmpContact() {
		return empContact;
	}

	public void setEmpContact(Long empContact) {
		this.empContact = empContact;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}

	public List<Skillset> getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(List<Skillset> skillSet) {
		this.skillSet = skillSet;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empContact=" + empContact + ", empEmail="
				+ empEmail + ", empPhoto=" + empPhoto + ", skillSet=" + skillSet + "]";
	}

}
