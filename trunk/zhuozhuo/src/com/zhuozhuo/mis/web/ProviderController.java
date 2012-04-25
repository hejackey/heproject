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
import com.zhuozhuo.mis.model.Provider;
import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.PProvider;
import com.zhuozhuo.mis.service.ProviderService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.StringUtils;

public class ProviderController  extends BaseMultiActionController {
	private static final String providerListView = "provider/listProviders";		
	private static final String providerFormView = "provider/providerForm";
	private static final String editProviderForm = "provider/editProviderForm";
	private static final String listProviders = "listProvider.do";
	private static final String queryProviderView = "provider/queryProvider";

	private ProviderService providerService;
	
	/**
	 * 查询供应商
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listProviders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryProvider queryProvider=new QueryProvider();		
		bind(request,queryProvider);
		ModelAndView mv;
		List<PProvider> listProviders;
		if((queryProvider.getAccount()==null)&&(queryProvider.getAddress()==null)&&(queryProvider.getProviderCode()==null)&&(queryProvider.getProviderName()==null)){
			listProviders=providerService.listProviders();
		}else{
			listProviders=providerService.listProviders(queryProvider);
		}				
		mv = new ModelAndView(providerListView,"listProviders", listProviders);			
		return mv;
	}
	
	public ModelAndView queryProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryProvider queryProvider=new QueryProvider();		
		bind(request,queryProvider);
		ModelAndView mv;
		if((queryProvider.getAccount()==null)&&(queryProvider.getAddress()==null)&&(queryProvider.getProviderCode()==null)&&(queryProvider.getProviderName()==null)){
			mv = new ModelAndView(queryProviderView,"command",queryProvider);
		}else{
			List<PProvider> list=providerService.queryProvider(queryProvider);			
			Map map=new HashMap();
			map.put("command",queryProvider);
			map.put("providers",list);			
			mv = new ModelAndView(queryProviderView,map);
		}		
		return mv;
	}
	
	/**
	 * 增加或修改供应商
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addEditProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		ModelAndView mv;
		Provider provider;
		if(id==null){
			provider=new Provider();
			mv = new ModelAndView(providerFormView, "command",provider);
		}else{
			provider=providerService.getProvider(id);
			mv = new ModelAndView(editProviderForm, "command",provider);
		}
		return mv;
	}
	
	/**
	 * 保存供应商
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Provider provider=new Provider();
		ModelAndView mv = new ModelAndView();
		if (!validation(request, provider, mv)) { //校验失败
			mv.setViewName(providerFormView);//跳转到修改页
			mv.addObject("command",provider);
		} else { //校验成功
			providerService.saveProvider(provider);
			View v = new RedirectView(listProviders);
			mv.setView(v);
		}
		return mv;
	}
	
	/**
	 * 更新供应商
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Provider provider = new  Provider();
		ModelAndView view = new ModelAndView();		
		if (!validation(request, provider, view)) { //校验失败
			view.setViewName(editProviderForm);//跳转到修改页
			view.addObject("command",provider);			
		} else { //校验成功			
			//更新数据库
			providerService.updateProvider(provider);
			View v = new RedirectView(listProviders);
			view.setView(v);			
		}
		return view;
	}
	
	/**
	 * 删除供应商
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteProviders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ids=request.getParameterValues("id");
		providerService.deleteProviders(ids);
		View v = new RedirectView(listProviders);
		ModelAndView mv = new ModelAndView(v);
		return mv;
	}	
	
	
	public ModelAndView qProviderByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryProvider queryProvider=new QueryProvider();		
		bind(request,queryProvider);
		ModelAndView mv;
		if(!StringUtils.isEmpty(queryProvider.getProviderName()))
			queryProvider.setProviderName(new String(queryProvider.getProviderName().getBytes("iso-8859-1"),"utf-8"));
		
		PageWraper pw =providerService.qProviderByName(queryProvider);
		if(pw!=null)
			queryProvider.setProviderList(pw.getResult());
		return new ModelAndView("/provider/qProviderResult","model",queryProvider);
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
}
