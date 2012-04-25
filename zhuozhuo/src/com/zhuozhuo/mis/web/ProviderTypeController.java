package com.zhuozhuo.mis.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.zhuozhuo.mis.common.BaseMultiActionController;
import com.zhuozhuo.mis.model.ProductType;
import com.zhuozhuo.mis.model.ProviderType;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.po.PProviderType;
import com.zhuozhuo.mis.service.ProviderTypeService;
import com.zhuozhuo.utils.NumberUtils;

public class ProviderTypeController extends BaseMultiActionController {
	private static final String providerTypeRoot = "0";//设置根目录的recordId为零
	private static final String parentName = "供应商类别";//设置根目录的名称
	
	private static final String providerTypeForm = "providerType/providerTypeForm";		
	private static final String providerTypeEdit = "providerType/providerTypeEdit";	
	private static final String providerTypeList = "providerType/providerTypeList";
	private static final String editProviderType = "providerType/editProviderType";
	private static final String redirectProviderTypeList = "listProviderType.do";
	
	private ProviderTypeService providerTypeService;
	
	/**
	 * 检索供应商类型，如果是根则检索所有的供应商类型，否则，只检索其所属子类型,包括生成树
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listProviderTypes(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String recordId=request.getParameter("recordId");
		if(recordId==null)
			recordId="0";		
		List<PProviderType> providerTypesList;
		ProviderType providerType=new ProviderType();
		
		if(recordId.equals(providerTypeRoot)){//说明是根
			providerTypesList=providerTypeService.getAllProviderType();
			providerType.setRecordId("0");			
			providerType.setParentName(null);
			providerType.setProviderytpeName("供应商类别");
		} else {
			providerTypesList=providerTypeService.getChildProviderType(recordId);
			providerType=providerTypeService.getProviderType(recordId);
		}
		
		Map map=new HashMap();
		map.put("providerTypesList",providerTypesList);
		List<PProviderType> list=providerTypeService.getProviderTypeTreeList();
		map.put("providerTypesTreeList",list);
		map.put("providerType",providerType);
		ModelAndView mv = new ModelAndView(providerTypeList,map);		
		return mv;		
	}	

	/**
	 * 增加供应商类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addEditProviderType(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String parentId=request.getParameter("parentId");
		ModelAndView mv = new ModelAndView(providerTypeForm,"command",new ProviderType());			
		return mv;
	}
	
	/**
	 * 保存供应商
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveProviderType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProviderType providerType=new ProviderType();
		ModelAndView mv = new ModelAndView();
		if (!validation(request, providerType, mv)) { //校验失败
			mv.setViewName(providerTypeForm);//跳转到修改页
			mv.addObject("command",providerType);
		} else { //校验成功
			providerTypeService.insertProviderType(providerType);
			View v = new RedirectView(redirectProviderTypeList);
			mv.setView(v);
		}
		return mv;
	}
	
	/**
	 * 修改一个供应商类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView editProviderType(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String recordId=request.getParameter("recordId");		
		ProviderType providerType=providerTypeService.getProviderType(recordId);
		ModelAndView mv = new ModelAndView(editProviderType, "command",providerType);		
		return mv;
	}
	
	public ModelAndView updateProviderType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProviderType  providerType  = new  ProviderType ();
		ModelAndView view = new ModelAndView();		
		if (!validation(request, providerType, view)) { //校验失败
			view.setViewName(editProviderType);//跳转到修改页
			view.addObject("command",providerType);			
		} else { //校验成功			
			//更新数据库
			providerTypeService.updateProviderType(providerType);
			View v = new RedirectView(redirectProviderTypeList);
			view.setView(v);			
		}
		return view;
	}
	
	public ModelAndView deleteProviderTypes(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String[] recordIds=request.getParameterValues("recordId");
		providerTypeService.deleteProviderTypes(recordIds);
		View v = new RedirectView(redirectProviderTypeList);
		ModelAndView mv = new ModelAndView(v);
		return mv;
	}
	
	public ProviderTypeService getProviderTypeService() {
		return providerTypeService;
	}
	
	public void setProviderTypeService(ProviderTypeService providerTypeService) {
		this.providerTypeService = providerTypeService;
	}
}
