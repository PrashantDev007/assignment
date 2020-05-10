package com.assignment.get_update_details_microservice.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.assignment.get_update_details_microservice.models.Login;

@EnableJpaRepositories
public interface LoginDao extends JpaRepository<Login, String> {

	@Query("UPDATE Login l SET l.contact =?1 WHERE l.loginid =?2")
	@Modifying(clearAutomatically = true)
	@Transactional
	public void UpdateContact(Long contact, String empID);

	@Query("SELECT l FROM Login l WHERE l.contact = ?1")
	public Login FindByContact(Long contact);

	@Query("SELECT l FROM Login l WHERE l.loginid = ?1")
	public Login fingByLoginId(String loginId);

}
