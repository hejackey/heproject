package com.zhuozhuo.mis.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zhuozhuo.mis.po.TAdmModule;
import com.zhuozhuo.mis.service.TAdmModuleService;
import com.zhuozhuo.mis.util.page.PageWraper;

import com.zhuozhuo.mis.vo.TModule;
import com.zhuozhuo.utils.StringUtils;

public class TAdmModuleController extends MultiActionController {
	private Logger log = Logger.getLogger(TAdmModuleController.class);
	private TAdmModuleService tadmModuleService;
	private final String LIST_VIEW="listModule.do";
	private final String EDIT_VIEW="/module/preAddModule";
	private final String EDIT_URL="preAddModule.do";
	
	/**
	 * 显示模块列表
	 */
	public ModelAndView listModule(HttpServletRequest request,
			HttpServletResponse response,TAdmModule o) {
		
		TAdmModule model=new TAdmModule();
		if(!"navigate".equals(request.getParameter("type")) && request.getSession().getAttribute("module_QueryVO")!=null){//记录查询的条件
			TModule vo = (TModule)request.getSession().getAttribute("module_QueryVO");	
			model.setQ_id(vo.getQ_id());
			model.setQ_parentid(vo.getQ_parentid());
			model.setQ_modulecode(vo.getQ_modulecode());
			model.setQ_modulename(vo.getQ_modulename());
			model.setQ_moduletype(vo.getQ_moduletype());
			model.setQ_forlog(vo.getQ_forlog());
			model.getPageInfo().setPage(vo.getPage());
			model.setQ_ifuse(vo.getQ_ifuse());
		}
		else{
			model=o;
		}
		
		try {		
			
			PageWraper pw = this.tadmModuleService.listTAdmModule(model);
			if(pw!=null)
				model.setModuleList(pw.getResult());		
			model.setModuleTreeList(this.tadmModuleService.getModuleTreeList());	
			
			if((model.getModuleList()==null || model.getModuleList().size()==0)
					&& request.getSession().getAttribute("module_QueryVO")==null
					&& "n".equals(request.getParameter("isQuery"))){
				request.getRequestDispatcher(this.EDIT_URL+"?id="+o.getQ_id()).forward(request, response);
				
				return null;
			}
			else{
				request.getSession().removeAttribute("module_QueryVO");
				
				return new ModelAndView("/module/listModule", "model", model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("listModule exception ,e===="+e.getMessage());	
			
			return new ModelAndView("/dataAccessFailure");
		}
				
		
	}
	
	public ModelAndView preAddModule(HttpServletRequest request,
			HttpServletResponse response,TAdmModule o){
		try{
			//保存列表页查询条件
			request.getSession().setAttribute("module_QueryVO", this.formatVoModule(request));
			//------修改模块------//
			if(!StringUtils.isEmpty(o.getId())){
				
				TAdmModule model = this.tadmModuleService.getModule(o.getId());
				
				return new ModelAndView(EDIT_VIEW,"model",model);
			}
			//------修改模块------//
			
			//------新增模块------//						
			return new ModelAndView(EDIT_VIEW,"model",o);
			//------新增模块------//
		}catch(Exception e){
			e.printStackTrace();
			log.error("preAddModule exception,parentid====="+o.getParentid());
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	/**
	 * 保存新增模块
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView saveModule(HttpServletRequest request,
			HttpServletResponse response,TAdmModule o){
		try{			
			//验证表单
			String validResult = this.validatorModuleForm(o);
			if(!StringUtils.isEmpty(validResult)){
				o.setErrors(validResult);
				return new ModelAndView(EDIT_VIEW,"model",o);
			}
			
			int result = this.tadmModuleService.saveModule(o);			
			
			if(result>0){
				o.setResult("1");
				//return new ModelAndView(EDIT_VIEW,"model",o);
				request.getRequestDispatcher(this.LIST_VIEW+"?id="+o.getQ_id()).forward(request, response);
				
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
	 * 保存修改模块
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView editModule(HttpServletRequest request,
			HttpServletResponse response,TAdmModule o){
		try{
			String validResult = this.validatorModuleForm(o);
			if(!StringUtils.isEmpty(validResult)){
				o.setErrors(validResult);
				return new ModelAndView(EDIT_VIEW,"model",o);
			}
						
			int result = this.tadmModuleService.editModule(o);
			
			if(result>0){
				o.setResult("2");
				//return new ModelAndView(EDIT_VIEW,"model",o);
				request.getRequestDispatcher(this.LIST_VIEW).forward(request, response);
				return null;
			}
			else{
				return new ModelAndView("/dataAccessFailure");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("editModule exception");
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	/**
	 * 修改部门状态
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateStatusModule(HttpServletRequest request,
			HttpServletResponse response,TAdmModule ox){
		try{					
			if(ox.getModuleid()!=null&&!StringUtils.isEmpty(request.getParameter("flag"))){
				String[] idArray = ox.getModuleid();												
				
				this.tadmModuleService.updateStatusModule(idArray,Integer.parseInt(request.getParameter("flag")));
				
				request.getRequestDispatcher("listModule.do?result=1").forward(request, response);						
				return null;
			}
			else{
				log.error("updateStatusModule error parameter is null");
				return new ModelAndView("/dataAccessFailure");			
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("updateStatusModule exception");
			
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	public TModule formatVoModule(HttpServletRequest request){
		TModule vo = new TModule();
		vo.setQ_id(request.getParameter("q_id"));
		vo.setQ_modulecode(request.getParameter("q_modulecode"));
		vo.setQ_modulename(request.getParameter("q_modulename"));		
		vo.setQ_parentid(request.getParameter("q_parentid"));
		
		if(StringUtils.isEmpty(request.getParameter("q_moduletype")))
			vo.setQ_moduletype(0);
		else
			vo.setQ_moduletype(Integer.parseInt(request.getParameter("q_moduletype")));
		
		if(StringUtils.isEmpty(request.getParameter("pageInfo.page")))
			vo.setPage(0);
		else
			vo.setPage(Integer.parseInt(request.getParameter("pageInfo.page")));
		
		if(StringUtils.isEmpty(request.getParameter("q_forlog")))
			vo.setQ_forlog(0);
		else
			vo.setQ_forlog(Integer.parseInt(request.getParameter("q_forlog")));
		
		if(StringUtils.isEmpty(request.getParameter("q_ifuse")))
			vo.setQ_ifuse(100);
		else
			vo.setQ_ifuse(Integer.parseInt(request.getParameter("q_ifuse")));
		
		return vo;
	}

	public String validatorModuleForm(TAdmModule module){
		try {
			if(StringUtils.isEmpty(module.getModuleName())
					||new String(module.getModuleName().getBytes(),"utf-8").length()>30)
				return "模块名称不能为空,且长度不能超过30";
			
			if(StringUtils.isEmpty(module.getModuleCode())
					||new String(module.getModuleCode().getBytes(),"utf-8").length()>30)
				return "模块编号不能为空,且长度不能超过30";			
		} catch (UnsupportedEncodingException e) {
			return "表单参数有错";
		}
		return "";
	}
	public TAdmModuleService getTadmModuleService() {
		return tadmModuleService;
	}

	public void setTadmModuleService(TAdmModuleService tadmModuleService) {
		this.tadmModuleService = tadmModuleService;
	}
}
