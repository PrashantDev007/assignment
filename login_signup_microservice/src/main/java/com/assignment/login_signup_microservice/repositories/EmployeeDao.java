package com.assignment.login_signup_microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.assignment.login_signup_microservice.models.Employee;

@EnableJpaRepositories
public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
