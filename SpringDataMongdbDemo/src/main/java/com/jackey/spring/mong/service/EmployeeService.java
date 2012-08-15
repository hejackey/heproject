package com.jackey.spring.mong.service;

import java.util.List;

import com.jackey.spring.mong.model.Employee;

public interface EmployeeService {
	/**
	 * 保存对象
	 * @param employ
	 */
	public void saveEmployee(Employee employ);
	/**
	 * 更新对象
	 * @param employ
	 */
	public void updateEmployee(Employee employ);
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(String id);
	
	/**
	 * 返回所有记录数
	 * @return
	 */
	public List<Employee> getEmployeeList();
	
	/**
	 * 获取分页列表
	 * @param employ
	 * @param start 当前页起始下标
	 * @param limit	每页记录条数
	 * @return
	 */
	public List<Employee> getEmployeeByCond(Employee employ,int start,int limit);
}
