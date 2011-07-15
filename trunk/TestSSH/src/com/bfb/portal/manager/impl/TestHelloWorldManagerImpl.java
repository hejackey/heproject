package com.bfb.portal.manager.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bfb.portal.dao.HelloWorldDao;
import com.bfb.portal.manager.TestHelloWorldManager;
import com.bfb.portal.model.HelloWorld;

public class TestHelloWorldManagerImpl implements TestHelloWorldManager {
	private HelloWorldDao helloWorldDao;
	
	public String sayHello(String arg) {
		return "I'm from service layer,say hello to"+arg;
	}

	public HelloWorld getHelloWorld(int id) {
		return helloWorldDao.getHelloWorld(id);
	}

	public HelloWorldDao getHelloWorldDao() {
		return helloWorldDao;
	}

	public void setHelloWorldDao(HelloWorldDao helloWorldDao) {
		this.helloWorldDao = helloWorldDao;
	}

	@Transactional
	public HelloWorld saveHelloWorld(HelloWorld helloWorld) throws Exception {
		try{
			 helloWorldDao.saveHelloWorld(helloWorld);
			
			 return helloWorld;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception();
			//return null;
		}
	}

	public int getHelloWorldCount(HelloWorld model) {
		return helloWorldDao.getHelloWorldCount(model);
	}

	public List<HelloWorld> getHelloWorldList(HelloWorld model) {
		return helloWorldDao.getHelloWorldList(model);
	}

	public int updateHellworld(HelloWorld model) {
		return helloWorldDao.updateHellworld(model);
	}

	public int delHelloWorld(String id) {
		return helloWorldDao.delHelloWorld(id);
	}

}
