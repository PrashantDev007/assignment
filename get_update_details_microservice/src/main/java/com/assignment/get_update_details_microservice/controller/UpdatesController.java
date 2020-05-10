package com.assignment.get_update_details_microservice.controller;

import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.get_update_details_microservice.models.Employee;
import com.assignment.get_update_details_microservice.models.Skillset;
import com.assignment.get_update_details_microservice.repositories.EmployeeDao;
import com.assignment.get_update_details_microservice.repositories.LoginDao;
import com.assignment.get_update_details_microservice.repositories.SkillSetDao;
import com.assignment.get_update_details_microservice.util.JwtUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UpdatesController {

	private static final String REDIS_INDEX_KEY = "epployees";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private LoginDao loginDao;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private SkillSetDao skillSetDao;

//	@Cacheable(value = "employees",key = "#empid")
	@GetMapping("/getEmployeeData/{id}")
	public Optional<Employee> getEmployeeById(@RequestHeader(value = "Authorization") String h,
			@PathVariable("id") String empid) throws Exception {
		String s = h.substring(7);

		if (!empid.equals(jwtUtil.extractUsername(s))) {
			throw new Exception("incorrect jwt");
		}
		Optional<Employee> emp = null;

		emp = employeeDao.findById(empid);
		if (emp == null) {
			throw new Exception("employee does not exist");
		}

		return emp;
	}

//	@CacheEvict(value = "persons",key = "#e.empId")
	@PostMapping("/updateEmployeeById")
	public ResponseEntity updateEmployeeById(@RequestBody Employee e) {

		employeeDao.save(e);

		try {
			Iterator it = e.getSkillSet().iterator();
			while (it.hasNext()) {
				skillSetDao.save((Skillset) it.next());
			}
			loginDao.UpdateContact(e.getEmpContact(), e.getEmpId());
		} catch (Exception exception) {
			return ResponseEntity.badRequest().body("{message:some data is wrong}");
		}
		return ResponseEntity.badRequest().body("{ \"code\": 200,\r\n"
				+ "\"message\": [ \"successfully added \" ], \r\n" + "\"data\": {}\r\n" + "}    \r\n" + "");

	}

}
