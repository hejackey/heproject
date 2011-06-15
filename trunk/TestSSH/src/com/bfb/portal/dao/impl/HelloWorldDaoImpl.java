package com.bfb.portal.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bfb.portal.dao.HelloWorldDao;
import com.bfb.portal.model.HelloWorld;

public class HelloWorldDaoImpl extends SqlMapClientDaoSupport implements
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

}
