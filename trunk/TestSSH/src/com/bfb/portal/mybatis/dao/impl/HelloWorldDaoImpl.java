package com.bfb.portal.mybatis.dao.impl;

import com.bfb.portal.base.dao.BaseDao;
import com.bfb.portal.dao.HelloWorldDao;
import com.bfb.portal.model.HelloWorld;

public class HelloWorldDaoImpl extends BaseDao implements HelloWorldDao {

	public HelloWorld getHelloWorld(int id) {
		return (HelloWorld)this.getSqlSession().selectOne(String.valueOf(id));
	}

	public void saveHelloWorld(HelloWorld helloWorld) {
		this.getSqlSession().insert("HelloWorld.saveHelloWorld",helloWorld);
	}

}
