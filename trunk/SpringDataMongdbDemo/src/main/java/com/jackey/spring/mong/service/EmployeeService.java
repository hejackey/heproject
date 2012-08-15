package com.jackey.spring.mong.service;

import java.util.List;

import com.jackey.spring.mong.model.Employee;

public interface EmployeeService {
	/**
	 * �������
	 * @param employ
	 */
	public void saveEmployee(Employee employ);
	/**
	 * ���¶���
	 * @param employ
	 */
	public void updateEmployee(Employee employ);
	
	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(String id);
	
	/**
	 * �������м�¼��
	 * @return
	 */
	public List<Employee> getEmployeeList();
	
	/**
	 * ��ȡ��ҳ�б�
	 * @param employ
	 * @param start ��ǰҳ��ʼ�±�
	 * @param limit	ÿҳ��¼����
	 * @return
	 */
	public List<Employee> getEmployeeByCond(Employee employ,int start,int limit);
}
