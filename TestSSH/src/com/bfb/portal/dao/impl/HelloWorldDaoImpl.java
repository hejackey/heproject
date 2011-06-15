package com.bfb.portal.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bfb.portal.dao.HelloWorldDao;
import com.bfb.portal.model.HelloWorld;

public class HelloWorldDaoImpl extends SqlMapClientDaoSupport implements
		HelloWorldDao {

	public HelloWorld getHelloWorld(int id) {
		return (HelloWorld)this.getSqlMapClientTemplate().queryForObject("HelloWorld.getHelloWorld",id);
	}

}
