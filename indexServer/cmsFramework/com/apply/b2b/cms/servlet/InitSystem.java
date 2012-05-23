package com.apply.b2b.cms.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.apply.b2b.cms.config.ConfigBuilderFactory;

public class InitSystem extends HttpServlet{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8298371945476642300L;
	
	/**
	 * Log4J Logger for this class
	 */
	protected transient final Logger log  =  Logger.getLogger(this.getClass() );
	protected String realpath = null;
	
	/**
	 * Constructor of the object.
	 */
	public InitSystem(){
	}
	
	public void init() throws ServletException{
		ServletContext servletContext = this.getServletContext();
        realpath = servletContext.getRealPath("/");
        ConfigBuilderFactory.getConfigBuilder().setProperty("REAL_PATH", realpath);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init();
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.write("更新模板内容");
		writer.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}