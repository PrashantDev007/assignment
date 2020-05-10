package com.assignment.login_signup_microservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.assignment.login_signup_microservice.models.Login;

@EnableJpaRepositories
public interface LoginDao extends JpaRepository<Login, String> {

	@Query("SELECT l FROM Login l WHERE l.contact = ?1")
	public Login FindByContact(Long contact);

	@Query("SELECT l FROM Login l WHERE l.loginid = ?1")
	public Login fingByLoginId(String loginId);

}
