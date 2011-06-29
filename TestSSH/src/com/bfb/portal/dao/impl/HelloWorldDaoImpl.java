package com.bfb.portal.dao.impl;

import java.util.List;

import com.bfb.portal.base.dao.BaseIbatisDao;
import com.bfb.portal.dao.HelloWorldDao;
import com.bfb.portal.model.HelloWorld;

public class HelloWorldDaoImpl extends BaseIbatisDao implements
		HelloWorldDao {

	public HelloWorld getHelloWorld(int id) {
		try{
			System.out.print(id);
			return (HelloWorld)this.getSqlMapClientTemplate().queryForObject("HelloWorld.getHelloWorld",id);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public HelloWorld saveHelloWorld(HelloWorld helloWorld) {
		this.getSqlMapClientTemplate().insert("HelloWorld.saveHelloWorld",helloWorld);
		return helloWorld;
	}

	public int getHelloWorldCount(HelloWorld model) {
		return 0;
	}

	public List<HelloWorld> getHelloWorldList(HelloWorld model) {
		return null;
	}

}
