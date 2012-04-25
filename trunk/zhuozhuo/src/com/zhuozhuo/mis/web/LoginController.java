package com.zhuozhuo.mis.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zhuozhuo.mis.po.TAdmRole;
import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.web.validator.UserValidator;
import com.zhuozhuo.utils.Constants;
import com.zhuozhuo.utils.MD5Util;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class LoginController extends MultiActionController {
	private final String LOGIN_VIEW="/login/login";
	private final String LOGIN_URL="showLogin.do";
	private final String MAIN_VIEW="/login/main";
	private final String LEFT_VIEW="/login/left";
	private final String TOP_VIEW="/login/top";
	private final String BOTTOM_VIEW="/login/bottom";
	private final String CENTER_VIEW="/login/center";
	private final String SELLLIST_VIEW="/login/selllist";
	private ServiceFacotryUtil serviceFactoryUtil;
	
	public ModelAndView showLogin(HttpServletRequest request,HttpServletResponse response){
		TAdmUser user = new TAdmUser();
		return new ModelAndView(LOGIN_VIEW,"model",user);
	}
	
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,
			TAdmUser user){
		try{
			String validResult = UserValidator.validLoginInfo(request, user);
			if(!StringUtils.isEmpty(validResult)){
				user.setErrors(validResult);
				
				return new ModelAndView(LOGIN_VIEW,"model",user);
			}
			
			user.setPassword(MD5Util.MD5Encode(user.getPassword()+Constants.MD5_KEY));
			user = serviceFactoryUtil.getTadmUserService().isLogin(user);
			if(user==null){
				user = new TAdmUser();
				user.setErrors("用户名、密码错误，请重新输入");
				
				return new ModelAndView(LOGIN_VIEW,"model",user);
			}
			else{
				request.getSession().setAttribute("login_t_adm_user", user);
			}
			return new ModelAndView(MAIN_VIEW,"model",user);
		}catch(Exception e){
			e.printStackTrace();
			
			return new ModelAndView(LOGIN_VIEW,"model",user);
		}
	}

	public ModelAndView top(HttpServletRequest request,HttpServletResponse response){
		TAdmUser user = (TAdmUser)request.getSession().getAttribute("login_t_adm_user");
		
		return new ModelAndView(this.TOP_VIEW,"model",user);
	}
	public ModelAndView bottom(HttpServletRequest request,HttpServletResponse response){
		TAdmUser user = new TAdmUser();
		return new ModelAndView(this.BOTTOM_VIEW,"model",user);
	}
	public ModelAndView center(HttpServletRequest request,HttpServletResponse response){
		TAdmUser user = new TAdmUser();
		return new ModelAndView(this.CENTER_VIEW,"model",user);
	}
	public ModelAndView left(HttpServletRequest request,HttpServletResponse response){
		try{
			TAdmUser user = (TAdmUser)request.getSession().getAttribute("login_t_adm_user");
			TAdmRole role = user.getRole();
			role.setModuleList(this.serviceFactoryUtil.getTadmModuleService().getModuleTreeList());
			role.setRolePrivList(this.serviceFactoryUtil.getTadmRoleService().getRolePrilByRoleId(role.getId()));
			user.setRole(role);
			
			return new ModelAndView(this.LEFT_VIEW,"model",user);
		}
		catch(Exception e){
			return null;
		}
	}
	public ModelAndView selllist(HttpServletRequest request,HttpServletResponse response){
		TAdmUser user = new TAdmUser();
		return new ModelAndView(this.SELLLIST_VIEW,"model",user);
	}
	
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
		TAdmUser user = new TAdmUser();
		request.getSession().removeAttribute("login_t_adm_user");
		
		return new ModelAndView(this.LOGIN_VIEW,"model",user);
		
	}
	
	public ServiceFacotryUtil getServiceFactoryUtil() {
		return serviceFactoryUtil;
	}

	public void setServiceFactoryUtil(ServiceFacotryUtil serviceFactoryUtil) {
		this.serviceFactoryUtil = serviceFactoryUtil;
	}
}
