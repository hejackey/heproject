package com.zhuozhuo.mis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhuozhuo.mis.po.TAdmUser;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,
			Object handler)throws Exception{
		TAdmUser user = (TAdmUser)request.getSession().getAttribute("login_t_adm_user");
		
		if(user==null&&!request.getRequestURI().contains("showLogin.do")
				&&!request.getRequestURI().contains("submitLogin.do")){
			request.getRequestDispatcher("/login/showLogin.do").forward(request, response);
			//response.sendRedirect("/login/showLogin.do");
			return true;
		}else{
			return true;
		}
	}
}
