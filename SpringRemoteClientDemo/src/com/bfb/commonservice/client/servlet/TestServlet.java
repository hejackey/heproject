package com.bfb.commonservice.client.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bfb.commonservice.client.SimpleObject;
import com.bfb.commonservice.model.Account;

public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8903237495985365087L;

	public void doGet(HttpServletRequest req,HttpServletResponse res){
		WebApplicationContext wac = WebApplicationContextUtils.
		getRequiredWebApplicationContext(this.getServletContext());

		SimpleObject so = (SimpleObject)wac.getBean("sobject");
		
		Account account = new Account();
		account.setName("hello spring rmi!");
		System.out.println(so.getActService().insertAccount(account));

	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		doGet(req,res);
	}
}
