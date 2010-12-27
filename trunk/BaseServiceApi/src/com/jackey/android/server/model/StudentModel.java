package com.jackey.android.server.model;

import java.io.Serializable;

public class StudentModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 842997148175799624L;
	private String id;
	private String name;
	private int age;
	private String sex;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
