package com.jackey.android.server.openapi;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackey.android.server.model.StudentModel;
import com.jackey.android.server.util.GsonUtil;

public class TestServerApi extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108755470950226657L;
	public void init(){
		
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		String type = req.getParameter("type");
		String id = req.getParameter("id");
		log("type==="+type);
		log("id===="+id);
		try {
			if("json".equals(type) && "1".equals(id)){
				StudentModel student = new StudentModel();
				student.setId("1");
				student.setName("zhangsan");
				student.setAge(18);
				student.setSex("ÄÐ");
				res.setContentType("text/html;charset=UTF-8");
				res.getWriter().println(GsonUtil.toGson(student));
			}
			else
				res.getWriter().println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
