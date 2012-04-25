package com.zhuozhuo.mis.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.service.TAdmDeptService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.mis.vo.TDept;
import com.zhuozhuo.utils.Constants;
import com.zhuozhuo.utils.StringUtils;

public class TAdmDeptController extends BaseMultiActionController {
	private TAdmDeptService tadmDeptService;
	private Logger log = Logger.getLogger(TAdmDeptController.class);
	private final String LIST_VIEW="listDept.do";
	private final String EDIT_VIEW="/dept/preAddDept";
	/**
	 * 显示部门列表
	 */
	public ModelAndView listDept(HttpServletRequest request,
			HttpServletResponse response,TAdmDept o) {
		
		TAdmDept model=new TAdmDept();
		if(!"navigate".equals(request.getParameter("type")) && request.getSession().getAttribute("dept_QueryVO")!=null){//记录查询的条件
			TDept vo = (TDept)request.getSession().getAttribute("dept_QueryVO");	
			model.setQ_id(vo.getQ_id());
			model.setQ_parentid(vo.getQ_parentid());
			model.setQ_depcode(vo.getQ_depcode());
			model.setQ_depname(vo.getQ_depname());
			model.setQ_ifuse(vo.getQ_ifuse());
			model.getPageInfo().setPage(vo.getPage());
		}
		else{
			model=o;
		}
		
		try {		
			if(model.getQ_ifuse()==null)
				model.setQ_ifuse(100);
			PageWraper pw = this.tadmDeptService.listTAdmDept(model);
			if(pw!=null)
				model.setListDept(pw.getResult());		
			
			model.setDeptTreeList(this.tadmDeptService.getDeptTreeList());
									
			if((model.getListDept()==null || model.getListDept().size()==0)
					//末级节点修改返回列表
					&& request.getSession().getAttribute("dept_QueryVO")==null
					&& "n".equals(request.getParameter("isQuery"))){
				//return new ModelAndView("/dept/listDept", "model", model);
				request.getRequestDispatcher("preAddDept.do?id="+o.getQ_id()).forward(request, response);
				return null;
			}
			else{
				request.getSession().removeAttribute("dept_QueryVO");
				
				return new ModelAndView("/dept/listDept", "model", model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("listTadmDept exception ,e===="+e.getMessage());
			
			return new ModelAndView("/dataAccessFailure");
		}
	}

	/**
	 * 进入新增部门页面
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView preAddDept(HttpServletRequest request,
			HttpServletResponse response,TAdmDept o){
		try{
			//保存列表页查询条件
			request.getSession().setAttribute("dept_QueryVO", this.formatVoDept(request));
			//------修改部门------//
			if(!StringUtils.isEmpty(o.getId())){
				//加缓存操作
				TAdmDept model = this.tadmDeptService.getDept(o.getId());
				
				return new ModelAndView(EDIT_VIEW,"model",model);
			}
			//------修改部门------//
			
			//------新增部门------//
			TAdmDept model =o;				
			model.setParentname(this.tadmDeptService.getDeptParentName(model.getParentid()));
			model.setLevels(Constants.getObjectLevels(model.getParentname()));

			if(o.getIfuse()==null)
				model.setIfuse(1);
			if(o.getIsleaf()==null)
				model.setIsleaf(1);
			
			return new ModelAndView(EDIT_VIEW,"model",model);
			//------新增部门------//
		}catch(Exception e){
			e.printStackTrace();
			log.error("preAddDept exception,parentid====="+o.getParentid());
			return new ModelAndView("/dataAccessFailure");
		}
	}
	/*
	*//**
	 * 进入编辑部门页面
	 * @param request
	 * @param response
	 * @return
	 *//*
	public ModelAndView preEditDept(HttpServletRequest request,
			HttpServletResponse response){
		try{
			if(StringUtils.isEmpty(request.getParameter("id"))){
				log.error("preEditDept param error ,id is null");
				return new ModelAndView("/dataAccessFailure");
			}			
			//加缓存操作
			TAdmDept model = this.tadmDeptService.getDept(request.getParameter("id"));
			
			return new ModelAndView(EDIT_VIEW,"model",model);
		}catch(Exception e){
			e.printStackTrace();
			log.error("preEditDept exception");
			return new ModelAndView("/dataAccessFailure");
		}
	}
	*/
	/**
	 * 保存新增部门
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView saveDept(HttpServletRequest request,
			HttpServletResponse response,TAdmDept o){
		try{			
			//验证表单
			String validResult = this.validatorDeptForm(o);
			if(!StringUtils.isEmpty(validResult)){
				o.setErrors(validResult);
				return new ModelAndView(EDIT_VIEW,"model",o);
			}
			
			int result = this.tadmDeptService.saveDept(o);			
			
			if(result>0){
				o.setResult("1");
				//return new ModelAndView(EDIT_VIEW,"model",o);
				request.getRequestDispatcher(this.LIST_VIEW).forward(request, response);
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
	 * 保存修改部门
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView editDept(HttpServletRequest request,
			HttpServletResponse response,TAdmDept o){
		try{
			String validResult = this.validatorDeptForm(o);
			if(!StringUtils.isEmpty(validResult)){
				o.setErrors(validResult);
				return new ModelAndView(EDIT_VIEW,"model",o);
			}
						
			int result = this.tadmDeptService.editDept(o);
			
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
			log.error("editDept exception");
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	/**
	 * 修改部门状态
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateStatusDept(HttpServletRequest request,
			HttpServletResponse response,TAdmDept ox){
		try{					
			if(ox.getDeptid()!=null&&!StringUtils.isEmpty(request.getParameter("flag"))){
				String[] idArray = ox.getDeptid();												
				
				this.tadmDeptService.updateStatusDept(idArray,Integer.parseInt(request.getParameter("flag")));
				
				request.getRequestDispatcher(this.LIST_VIEW+"?result=1").forward(request, response);						
				return null;
			}
			else{
				log.error("updateStatusDept error parameter is null");
				return new ModelAndView("/dataAccessFailure");			
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("updateStatusDept exception");
			
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	public TDept formatVoDept(HttpServletRequest request){
		TDept vo = new TDept();
		vo.setQ_id(request.getParameter("q_id"));
		vo.setQ_parentid(request.getParameter("q_parentid"));
		vo.setQ_depcode(request.getParameter("q_depcode"));
		vo.setQ_depname(request.getParameter("q_depname"));
		
		if(StringUtils.isEmpty(request.getParameter("q_ifuse")))
			vo.setQ_ifuse(100);
		else
			vo.setQ_ifuse(Integer.parseInt(request.getParameter("q_ifuse")));
		
		if(StringUtils.isEmpty(request.getParameter("pageInfo.page")))
			vo.setPage(0);
		else
			vo.setPage(Integer.parseInt(request.getParameter("pageInfo.page")));
		
		return vo;
	}
	
	public String validatorDeptForm(TAdmDept dept){
		try {
			if(StringUtils.isEmpty(dept.getDepartmentname())
					||new String(dept.getDepartmentname().getBytes(),"utf-8").length()>50)
				return "部门名称不能为空,且长度不能超过50";
			
			if(StringUtils.isEmpty(dept.getDepartmentcode())
					||new String(dept.getDepartmentcode().getBytes(),"utf-8").length()>50)
				return "部门编号不能为空,且长度不能超过50";
			
			if(dept.getLevels()==null)
				return "层级不能为空";
		} catch (UnsupportedEncodingException e) {
			return "表单参数有错";
		}
		
		return "";
	}
	public TAdmDeptService getTadmDeptService() {
		return tadmDeptService;
	}

	public void setTadmDeptService(TAdmDeptService tadmDeptService) {
		this.tadmDeptService = tadmDeptService;
	}

}
