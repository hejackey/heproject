package com.bfb.portal.manager;

import java.util.List;

import com.bfb.portal.model.HelloWorld;

public interface TestHelloWorldManager {
	public String sayHello(String arg);
	public HelloWorld getHelloWorld(int id);
	public void saveHelloWorld(HelloWorld helloWorld);
	public List<HelloWorld> getHelloWorldList(HelloWorld model);
	public int getHelloWorldCount(HelloWorld model);
}
