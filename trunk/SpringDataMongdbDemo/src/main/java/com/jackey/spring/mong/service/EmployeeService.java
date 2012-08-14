package com.jackey.spring.mong.service;

import java.util.List;

import com.jackey.spring.mong.model.Employee;

public interface EmployeeService {
	public void saveEmployee(Employee employ);
	public void updateEmployee(Employee employ);
	public Employee getEmployeeById(String id);
	public List<Employee> getEmployeeList();
}
