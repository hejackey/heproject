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
	private static int unsafeParam;//无论该变量是否定义成static，多线程环境下都是不安全的
	private volatile int safeParam;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		System.out.println("doGet ===============");
		int safeP=0;//局部变量，多线程环境下是安全的
		if("1".equals(req.getParameter("type"))){
			testUnsafeParam(safeP);
		}
		unsafeParam = 1;
		safeParam = 2;
		safeP=3;
		System.out.println("doGet ==============="+unsafeParam);
		System.out.println("doGet =====safeParam=========="+safeParam);
		System.out.println("doGet =====param=========="+safeP);
		try {
			res.getWriter().write("test struts servlet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		System.out.println("doPost ===============");
	}
	
	public void testUnsafeParam(int param){
		System.out.println("testUnsafeParam ==============="+unsafeParam);
		System.out.println("testUnsafeParam =====safeParam=========="+safeParam);
		System.out.println("testUnsafeParam =====param=========="+param);
	}
}
