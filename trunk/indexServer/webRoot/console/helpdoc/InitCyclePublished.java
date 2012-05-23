package com.mic.b2b.cms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class InitCyclePublished extends HttpServlet {
	public static volatile int PUBLISHED_FLAG = 0;
	
	public void init() throws ServletException {
	}
	
	public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		try {
			String type = request.getParameter("type");
			if (StringUtils.isNotEmpty(type) && "1".equals(type)) {
				if (PUBLISHED_FLAG == 1)
					PUBLISHED_FLAG = 2;
			} else if (StringUtils.isNotEmpty(type) && "2".equals(type)) {
				if (PUBLISHED_FLAG == 2)
					PUBLISHED_FLAG = 1;
			} else if (StringUtils.isNotEmpty(type) && "3".equals(type)) {
				if (PUBLISHED_FLAG == 3)
					PUBLISHED_FLAG = 0;
			} else if (StringUtils.isNotEmpty(type) && "4".equals(type)) {
				if (PUBLISHED_FLAG == 1 || PUBLISHED_FLAG == 2)
					PUBLISHED_FLAG = 3;
			}
			
			if ("0".equals(PUBLISHED_FLAG)) {
				PUBLISHED_FLAG = "1";
				Published published = new Published();
				// published.publishedShowroom();
				published.start();
				// PUBLISHED_FLAG = "3";
				response.sendRedirect("/cmscontrol/StopSuccess.jsp");
			} else if ("1".equals(PUBLISHED_FLAG)) {
				rd = this.getServletContext().getRequestDispatcher(
						"/cmscontrol/Success.jsp");
				rd.forward(request, response);
			} else if ("2".equals(PUBLISHED_FLAG)) {
				rd = this.getServletContext().getRequestDispatcher(
						"/cmscontrol/StopSuccess.jsp");
				rd.forward(request, response);
			} else if ("3".equals(PUBLISHED_FLAG)) {
				rd = this.getServletContext().getRequestDispatcher(
						"/cmscontrol/stoping.jsp"); 
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rd = this.getServletContext().getRequestDispatcher(
					"/cmscontrol/error.jsp");
			rd.forward(request, response);
		}
	}
	
	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	// Clean up resources
	public void destroy() {
	}
}