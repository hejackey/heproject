package com.bfb.portal.mybatis.dao.impl;

import java.util.List;

import com.bfb.portal.base.dao.BaseMybatisDao;
import com.bfb.portal.dao.HelloWorldDao;
import com.bfb.portal.model.HelloWorld;

public class HelloWorldDaoImpl extends BaseMybatisDao implements HelloWorldDao {

	public HelloWorld getHelloWorld(int id) {
		return (HelloWorld)this.getSqlSession().selectOne("getHelloWorld",String.valueOf(id));
	}

	public HelloWorld saveHelloWorld(HelloWorld helloWorld) {
		helloWorld.setId(this.createHellWorldSeq());
		//this.getSqlSession().insert("HelloWorld.saveHelloWorld",helloWorld);
		this.getSqlSession().insert("HelloWorld.saveHelloWorldAll",helloWorld);
		
		return helloWorld;
	}

	public int getHelloWorldCount(HelloWorld model) {
		return Integer.valueOf(this.getSqlSession().selectOne("HelloWorld.getHelloWorldCount",model).toString());
	}

	public List<HelloWorld> getHelloWorldList(HelloWorld model) {
		return this.getSqlSession().selectList("HelloWorld.getHelloWorldList",model);
	}

	public int createHellWorldSeq() {
		return Integer.valueOf(this.getSqlSession().selectOne("HelloWorld.getHellWorldSeq").toString());
	}

	public int updateHellworld(HelloWorld model) {
		return this.getSqlSession().update("HelloWorld.updateHellworld",model);
	}

}
