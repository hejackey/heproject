package com.bfb.portal.mybatis.dao.impl;

import java.util.List;

import com.bfb.portal.base.dao.BaseDao;
import com.bfb.portal.dao.HelloWorldDao;
import com.bfb.portal.model.HelloWorld;

public class HelloWorldDaoImpl extends BaseDao implements HelloWorldDao {

	public HelloWorld getHelloWorld(int id) {
		return (HelloWorld)this.getSqlSession().selectOne("getHelloWorld",String.valueOf(id));
	}

	public void saveHelloWorld(HelloWorld helloWorld) {
		this.getSqlSession().insert("HelloWorld.saveHelloWorld",helloWorld);
	}

	public int getHelloWorldCount(HelloWorld model) {
		return Integer.valueOf(this.getSqlSession().selectOne("HelloWorld.getHelloWorldCount",model).toString());
	}

	public List<HelloWorld> getHelloWorldList(HelloWorld model) {
		return this.getSqlSession().selectList("HelloWorld.getHelloWorldList",model);
	}

}
