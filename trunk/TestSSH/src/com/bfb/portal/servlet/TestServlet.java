package com.bfb.portal.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645562637814865539L;

	public void doGet(HttpServletRequest req,HttpServletResponse res){
		System.out.println("doGet ===============");
		try {
			res.getWriter().write("test struts servlet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		System.out.println("doPost ===============");
	}
}
