package com.assignment.login_signup_microservice.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.imageio.spi.ServiceRegistry;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import org.apache.coyote.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.login_signup_microservice.models.AuthenticationRequest;
import com.assignment.login_signup_microservice.models.AuthenticationResponse;
import com.assignment.login_signup_microservice.models.Employee;
import com.assignment.login_signup_microservice.models.GeneratePassword;
import com.assignment.login_signup_microservice.models.Login;
import com.assignment.login_signup_microservice.models.Skillset;
import com.assignment.login_signup_microservice.repositories.EmployeeDao;
import com.assignment.login_signup_microservice.repositories.LoginDao;
import com.assignment.login_signup_microservice.repositories.SkillSetDao;
import com.assignment.login_signup_microservice.response.ResponseData;
import com.assignment.login_signup_microservice.services.MyUserDetailsService;
import com.assignment.login_signup_microservice.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import antlr.collections.List;
import jdk.nashorn.api.tree.ClassExpressionTree;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable;

@RestController
public class HelloController {

	@Autowired
	private GeneratePassword generatePassword;

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private SkillSetDao skillSetDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/saveEmployee")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) throws JsonProcessingException {

		try {
			Iterator it = employee.getSkillSet().iterator();

			while (it.hasNext()) {
				skillSetDao.save((Skillset) it.next());
			}
			employeeDao.save(employee);
			Login login = new Login();
			login.setLoginId(employee.getEmpId());
			login.setPassword(generatePassword.generatePassword());
			login.setContact(employee.getEmpContact());
			loginDao.save(login);
		} catch (Exception e) {

			return ResponseEntity.badRequest().body("{message:contact number cannot be same}");
		}

		ObjectMapper mapper = new ObjectMapper();

		String response = mapper.writeValueAsString(new ResponseData("200", "successfully added", ""));

		return ResponseEntity.status(200)
				.body("{ \"code\": 201,\r\n"
						+ "\"message\": [ \"successfully added \" ], \r\n"
						+ "\"data\": {}\r\n"
						+ "}\r\n" + "");

	}

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		Long contact = authenticationRequest.getContact();
		Login l = null;

		l = loginDao.FindByContact(contact);

		if (l == null) {
			return ResponseEntity.badRequest().body("{message: wrong cretentials}");
		} else if (!l.getPassword().equals(authenticationRequest.getPassword())) {
			return ResponseEntity.badRequest().body("{message: wrong cretentials}");
		}

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(l.getLoginId(), l.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("incorrect name or password " + e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(l.getLoginId());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
