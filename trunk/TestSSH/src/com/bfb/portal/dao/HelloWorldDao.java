package com.bfb.portal.dao;

import java.util.List;

import com.bfb.portal.model.HelloWorld;

public interface HelloWorldDao {
	public HelloWorld getHelloWorld(int id);
	public HelloWorld saveHelloWorld(HelloWorld helloWorld);
	public List<HelloWorld> getHelloWorldList(HelloWorld model);
	public int getHelloWorldCount(HelloWorld model);
}
