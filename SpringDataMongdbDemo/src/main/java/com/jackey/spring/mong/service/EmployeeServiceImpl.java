package com.jackey.spring.mong.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.jackey.spring.mong.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	private static final String COLLECTION_NAME="spring_data";
	private MongoTemplate mongoTemplate; 
	
	@Override
	public void saveEmployee(Employee employ) {
		mongoTemplate.save(employ,COLLECTION_NAME);
	}

	@Override
	public void updateEmployee(Employee employ) {
		Update update = new Update();
		update.set("firstname", employ.getFirstname());
		update.set("age", employ.getAge());
		update.set("lastname", employ.getLastname());
		
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(employ.getId())), 
				update,
				COLLECTION_NAME);
	}

	@Override
	public Employee getEmployeeById(String id) {
		return (Employee) mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Employee.class,COLLECTION_NAME);
	}

	@Override
	public List<Employee> getEmployeeList() {
		return mongoTemplate.findAll(Employee.class,COLLECTION_NAME);
	}

	/**
	 * sort按id条件来排序，已mongdb中转换后的_id来排序
	 */
	@Override
	public List<Employee> getEmployeeByCond(Employee employ,int start,int limit) {
		Query query = new Query();
		query.addCriteria(Criteria.where("age").is(employ.getAge()));
		query.skip(start).limit(limit).sort().on("_id", Order.DESCENDING);
		
		return mongoTemplate.find(query, Employee.class,COLLECTION_NAME);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
