package com.zhuozhuo.mis.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.zhuozhuo.mis.po.TAdmRole;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class TAdmRoleController extends MultiActionController {
	private Logger log = Logger.getLogger(TAdmRoleController.class);
	private ServiceFacotryUtil serviceFactoryUtil;
	private final String LIST_URL="listRole.do";
	private final String EDIT_VIEW="/role/preAddRole";
	
	/**
	 * 显示部门列表
	 */
	public ModelAndView listRole(HttpServletRequest request,
			HttpServletResponse response,TAdmRole o) {
		
		TAdmRole model=new TAdmRole();
		if(!"navigate".equals(request.getParameter("type")) && request.getSession().getAttribute("role_QueryVO")!=null){//记录查询的条件
			model = (TAdmRole)request.getSession().getAttribute("role_QueryVO");			
		}
		else{
			model=o;
		}
		
		try {		
			
			PageWraper pw = this.serviceFactoryUtil.getTadmRoleService().listTAdmRole(model);
			if(pw!=null)
				model.setRoleList(pw.getResult());					
		} catch (Exception e) {
			e.printStackTrace();
			log.error("listRole exception ,e===="+e.getMessage());			
		}
				
		request.getSession().removeAttribute("role_QueryVO");
		return new ModelAndView("/role/listRole", "model", model);
	}
	
	public ModelAndView preAddRole(HttpServletRequest request,
			HttpServletResponse response,TAdmRole o){		
		try{
			//保存列表页查询条件
			request.getSession().setAttribute("role_QueryVO", o);
			
			//------修改用户------//
			if(!StringUtils.isEmpty(o.getId())){				
				o = this.serviceFactoryUtil.getTadmRoleService().getRoleById(o.getId());
				o.setRolePrivList(this.serviceFactoryUtil.getTadmRoleService().getRolePrilByRoleId(o.getId()));
				//return new ModelAndView(EDIT_VIEW,"model",model);
			}
			//------修改用户------//
			
			o.setModuleList(this.serviceFactoryUtil.getTadmModuleService().getModuleTreeList());
			
			//------新增用户------//									
			return new ModelAndView(EDIT_VIEW,"model",o);
			//------新增用户------//
		}catch(Exception e){
			e.printStackTrace();
			log.error("TAdmRoleController exception");
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	public ModelAndView saveRole(HttpServletRequest request,
			HttpServletResponse response,TAdmRole o){
		try{			
			//验证表单
			String validResult = this.validatorRoleForm(o);
			if(!StringUtils.isEmpty(validResult)){
				o.setErrors(validResult);
				return new ModelAndView(EDIT_VIEW,"model",o);
			}			
			
			o.setCreateBy("2");			
			int result = this.serviceFactoryUtil.getTadmRoleService().saveRole(o);			
			
			if(result>0){
				o.setResult("1");
				o.setModuleList(this.serviceFactoryUtil.getTadmModuleService().getModuleTreeList());
				o.setRolePrivList(this.serviceFactoryUtil.getTadmRoleService().getRolePrilByRoleId(o.getId()));
				
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
	 * 保存修改角色
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView editRole(HttpServletRequest request,
			HttpServletResponse response,TAdmRole o){
		try{
			String validResult = this.validatorRoleForm(o);
			if(!StringUtils.isEmpty(validResult)){
				o.setErrors(validResult);
				return new ModelAndView(EDIT_VIEW,"model",o);
			}
			
			int result = this.serviceFactoryUtil.getTadmRoleService().editRole(o);
			
			if(result>0){
				o.setResult("2");
				o.setModuleList(this.serviceFactoryUtil.getTadmModuleService().getModuleTreeList());
				o.setRolePrivList(this.serviceFactoryUtil.getTadmRoleService().getRolePrilByRoleId(o.getId()));
				
				//return new ModelAndView(EDIT_VIEW,"model",o);
				request.getRequestDispatcher(LIST_URL).forward(request, response);
				return null;
			}
			else{
				return new ModelAndView("/dataAccessFailure");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("editRole exception");
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	/**
	 * 修改角色状态
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateStatusRole(HttpServletRequest request,
			HttpServletResponse response,TAdmRole ox){
		try{					
			if(ox.getRoleid()!=null&&!StringUtils.isEmpty(request.getParameter("flag"))){
				String[] idArray = ox.getRoleid();												
				
				this.serviceFactoryUtil.getTadmRoleService().updateStatusRole(idArray,Integer.parseInt(request.getParameter("flag")));
				
				request.getRequestDispatcher("listRole.do?result=1").forward(request, response);						
				return null;
			}
			else{
				log.error("updateStatusRole error parameter is null");
				return new ModelAndView("/dataAccessFailure");			
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("updateStatusRole exception");
			
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	public String validatorRoleForm(TAdmRole o){
		try {
			if(StringUtils.isEmpty(o.getRoleName()) || new String(o.getRoleName().getBytes(),"utf-8").length()>60){
				return "角色名称不能为空，且长度不能超过60";
			}
			
		} catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
			return "表单参数有错";
		}
		return "";
	}

	public ServiceFacotryUtil getServiceFactoryUtil() {
		return serviceFactoryUtil;
	}

	public void setServiceFactoryUtil(ServiceFacotryUtil serviceFactoryUtil) {
		this.serviceFactoryUtil = serviceFactoryUtil;
	}
}
