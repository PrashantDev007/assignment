package com.assignment.login_signup_microservice.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.assignment.login_signup_microservice.models.Login;
import com.assignment.login_signup_microservice.repositories.LoginDao;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {

		Login l = null;
		l = loginDao.fingByLoginId(empId);
		return new User(l.getLoginId(), l.getPassword(), new ArrayList<>());
	}

}
