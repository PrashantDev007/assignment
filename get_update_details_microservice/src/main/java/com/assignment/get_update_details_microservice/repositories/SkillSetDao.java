package com.assignment.get_update_details_microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.assignment.get_update_details_microservice.models.Skillset;

@EnableJpaRepositories
public interface SkillSetDao extends JpaRepository<Skillset, Integer> {

}
