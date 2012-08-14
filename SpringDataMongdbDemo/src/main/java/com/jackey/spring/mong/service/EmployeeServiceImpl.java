package com.jackey.spring.mong.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.jackey.spring.mong.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private MongoOperations mongoOperation; 
	
	@Override
	public void saveEmployee(Employee employ) {
		mongoOperation.save(employ);
	}

	@Override
	public void updateEmployee(Employee employ) {
		mongoOperation.updateFirst(new Query(Criteria.where("id").is(employ.getId())), 
				Update.update("firstname",employ.getFirstname()),
				"updateEmployee");
	}

	@Override
	public Employee getEmployeeById(String id) {
		return (Employee) mongoOperation.findOne(new Query(Criteria.where("id").is(id)), Employee.class);
	}

	@Override
	public List<Employee> getEmployeeList() {
		return mongoOperation.findAll(Employee.class);
	}

}
