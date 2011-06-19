package com.bfb.portal.dao;

import com.bfb.portal.model.HelloWorld;

public interface HelloWorldDao {
	public HelloWorld getHelloWorld(int id);
	public void saveHelloWorld(HelloWorld helloWorld);
}
