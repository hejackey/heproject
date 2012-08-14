package com.jackey.spring.mong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jackey.spring.mong.model.Employee;
import com.jackey.spring.mong.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employService;
	
	@RequestMapping(value="/mong/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		Employee employ = new Employee();
		employ.setId("1232");
		employ.setAge(123);
		employ.setFirstname("ab");
		employ.setLastname("cd");
		
		employService.saveEmployee(employ);
	}
}
