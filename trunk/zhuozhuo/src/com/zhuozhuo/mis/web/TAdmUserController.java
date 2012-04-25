package com.zhuozhuo.mis.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.service.TAdmUserService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.mis.vo.TDept;
import com.zhuozhuo.utils.Constants;
import com.zhuozhuo.utils.MD5Util;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class TAdmUserController extends MultiActionController {
	private Logger log = Logger.getLogger(TAdmUserController.class);
	private ServiceFacotryUtil serviceFactoryUtil;
	private final String LIST_URL="listUser.do";
	private final String EDIT_VIEW="/user/preAddUser";
	/**
	 * 显示用户列表
	 */
	public ModelAndView listUser(HttpServletRequest request,
			HttpServletResponse response,TAdmUser o) {
		
		TAdmUser model=new TAdmUser();
		if(!"navigate".equals(request.getParameter("type")) && request.getSession().getAttribute("user_QueryVO")!=null){//记录查询的条件
			model = (TAdmUser)request.getSession().getAttribute("user_QueryVO");			
		}
		else{
			model=o;
		}
		
		try {		
			
			PageWraper pw = this.serviceFactoryUtil.getTadmUserService().listTAdmUser(model);
			if(pw!=null)
				model.setUserList(pw.getResult());					
		} catch (Exception e) {
			e.printStackTrace();
			log.error("listUser exception ,e===="+e.getMessage());			
		}
				
		request.getSession().removeAttribute("user_QueryVO");
		return new ModelAndView("/user/listUser", "model", model);
	}

	
	public ModelAndView preAddUser(HttpServletRequest request,
			HttpServletResponse response,TAdmUser o){		
		try{
			//保存列表页查询条件
			request.getSession().setAttribute("user_QueryVO", o);
			o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
			o.setRoleList(this.serviceFactoryUtil.getTadmRoleService().getRoleList());
			
			//------修改用户------//
			if(!StringUtils.isEmpty(o.getId())){				
				TAdmUser model = this.serviceFactoryUtil.getTadmUserService().getUser(o.getId());
				model.setDeptList(o.getDeptList());
				model.setRoleList(o.getRoleList());
				
				return new ModelAndView(EDIT_VIEW,"model",model);
			}
			//------修改用户------//
			
			//------新增用户------//									
			return new ModelAndView(EDIT_VIEW,"model",o);
			//------新增用户------//
		}catch(Exception e){
			e.printStackTrace();
			log.error("TAdmUserController exception");
			return new ModelAndView("/dataAccessFailure");
		}
	}

	public ModelAndView saveUser(HttpServletRequest request,
			HttpServletResponse response,TAdmUser o){
		try{			
			//验证表单
			String validResult = this.validatorUserForm(o);
			if(!StringUtils.isEmpty(validResult)){
				o.setErrors(validResult);
				return new ModelAndView(EDIT_VIEW,"model",o);
			}
			
			o.setCreateBy("2");
			o.setPassword(MD5Util.MD5Encode(o.getPassword()+Constants.MD5_KEY));
			
			int result = this.serviceFactoryUtil.getTadmUserService().saveUser(o);			
			
			if(result>0){
				o.setResult("1");
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				o.setRoleList(this.serviceFactoryUtil.getTadmRoleService().getRoleList());
				
				//return new ModelAndView(EDIT_VIEW,"model",o);
				request.getRequestDispatcher(LIST_URL).forward(request, response);
				
				return null;
			}
			else{
				return new ModelAndView("/dataAccessFailure");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("saveDept exception");
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	/**
	 * 保存修改用户
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView editUser(HttpServletRequest request,
			HttpServletResponse response,TAdmUser o){
		try{
			String validResult = this.validatorUserForm(o);
			if(!StringUtils.isEmpty(validResult)){
				o.setErrors(validResult);
				return new ModelAndView(EDIT_VIEW,"model",o);
			}
						
			if(!o.getPassword().equals(o.getHpasswd())){
				o.setPwdIsChange(1);
				o.setPassword(MD5Util.MD5Encode(o.getPassword()+Constants.MD5_KEY));
			}
			int result = this.serviceFactoryUtil.getTadmUserService().editUser(o);
			
			if(result>0){
				o.setResult("2");
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				o.setRoleList(this.serviceFactoryUtil.getTadmRoleService().getRoleList());
				//return new ModelAndView(EDIT_VIEW,"model",o);
				request.getRequestDispatcher(LIST_URL).forward(request, response);
				
				return null;
			}
			else{
				return new ModelAndView("/dataAccessFailure");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("editUser exception");
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	public String validatorUserForm(TAdmUser o){
		try {
			if(StringUtils.isEmpty(o.getLoginName())
					||new String(o.getLoginName().getBytes(),"utf-8").length()>30)
				return "登录名称不能为空,且长度不能超过30";
			
			if(StringUtils.isEmpty(o.getName())
					||new String(o.getName().getBytes(),"utf-8").length()>50)
				return "用户名不能为空,且长度不能超过50";		
			
			if(StringUtils.isEmpty(o.getPassword())
					||new String(o.getPassword().getBytes(),"utf-8").length()>32)
				return "密码不能为空,且长度不能超过32";	
		} catch (UnsupportedEncodingException e) {
			return "表单参数有错";
		}
		return "";
	}
	
	/**
	 * 修改用户状态
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateStatusUser(HttpServletRequest request,
			HttpServletResponse response,TAdmUser ox){
		try{					
			if(ox.getUserid()!=null&&!StringUtils.isEmpty(request.getParameter("flag"))){
				String[] idArray = ox.getUserid();												
				
				this.serviceFactoryUtil.getTadmUserService().updateStatusUser(idArray,Integer.parseInt(request.getParameter("flag")));
				
				request.getRequestDispatcher("listUser.do?result=1").forward(request, response);						
				return null;
			}
			else{
				log.error("updateStatusUser error parameter is null");
				return new ModelAndView("/dataAccessFailure");			
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("updateStatusUser exception");
			
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	public ServiceFacotryUtil getServiceFactoryUtil() {
		return serviceFactoryUtil;
	}

	public void setServiceFactoryUtil(ServiceFacotryUtil serviceFactoryUtil) {
		this.serviceFactoryUtil = serviceFactoryUtil;
	}

	
}
