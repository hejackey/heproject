package com.jackey.spring.mong.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jackey.spring.mong.model.Employee;
import com.jackey.spring.mong.service.EmployeeService;

@Controller
public class EmployeeController {
	private static Logger log = Logger.getLogger(EmployeeController.class.getName());
	
	@Autowired
	private EmployeeService employService;
	
	@RequestMapping(value="/mong/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		Employee employ = new Employee();
		employ.setId("1238");
		employ.setAge(12);
		employ.setFirstname("18");
		employ.setLastname("18");
		
		employService.saveEmployee(employ);
	}
	
	@RequestMapping(value="/mong/get")
	public void get(HttpServletRequest request,HttpServletResponse response){
		Employee employ = employService.getEmployeeById("1232");
		System.out.println(employ);
	}
	
	@RequestMapping(value="/mong/update")
	public void update(HttpServletRequest request,HttpServletResponse response){
		Employee employ = new Employee();
		employ.setId("1232");
		employ.setAge(2);
		employ.setFirstname("ert");
		employ.setLastname("english");
		
		employService.updateEmployee(employ);
	}
	
	@RequestMapping(value="/mong/list")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		List<Employee> list = employService.getEmployeeList();
		for(Employee employ : list){
			log.info(employ.getFirstname());
		}
	}
	
	@RequestMapping(value="/mong/page")
	public void getPageList(HttpServletRequest request,HttpServletResponse response){
		Employee employee = new Employee();
		employee.setAge(12);
		List<Employee> list = employService.getEmployeeByCond(employee,0,5);
		
		for(Employee employ : list){
			log.info(employ.getFirstname());
		}
	}
}
