package com.zhuozhuo.mis.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.zhuozhuo.mis.vo.TUser;


@SuppressWarnings("deprecation")
public class SampleMultiMethodController extends BaseMultiActionController {

	/*// 用户信息列表view
	private static final String userInfoListView = "listUser";
	// 用户信息编辑view
	private static final String userFormView = "preEditUser";
	// 提交成功后显示的view
	//private static final String userSuccessView = "redirect:listUser.do";
	// 用户信息列表key值
	private static final String userInfoListKey = "listUser.do";
	// userid
	private final String userIdParam = "id";
	// 定义业务对象
	private SampleService sampleService;

	public SampleService getSampleService() {
		return sampleService;
	}

	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	*//**
	 * 功能：获得所有的用户信息<br>
	 *//*
	public ModelAndView listUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TUser> userList = this.sampleService.listUser();
		ModelAndView mav = new ModelAndView(userInfoListView,"userList", userList);
		mav.addObject("relativeUrl", request.getContextPath());
		return mav;
	}
	
	*//**
	 * 跳转到增加用户页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ModelAndView preAddUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		return new ModelAndView("preAddUser","command",new TUser());
	}
	*//**
	 * 跳转到编辑用户界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ModelAndView preEditUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		TUser user = this.sampleService.getUser(id);
		return new ModelAndView(userFormView,"command",user);
	}

	*//**
	 * 功能：编辑用户信息<br>
	 *//*
	public ModelAndView edtiUser(HttpServletRequest request, HttpServletResponse response, TUser command) throws Exception {
		TUser tUser = (TUser) command;
		ServletRequestDataBinder binder = new ServletRequestDataBinder(command, getCommandName(command));
		BindException errors = binder.getErrors();
		this.sampleService.editUser(tUser);
		View v = new RedirectView(userInfoListKey);
		ModelAndView mav = new ModelAndView(v);
		return mav;
	}

	*//**
	 * 功能：保存修改或新增的用户信息<br>
	 *检查从页面bind的对象，如果userId或userName为空则返回原来的form页面;否则进行保存用户信息操作，返回 成功页面
	 *//*
	public ModelAndView saveUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TUser tUser = new  TUser();
		ModelAndView view = new ModelAndView();
		//ServletRequestDataBinder binder = new ServletRequestDataBinder(command, getCommandName(command));
		//BindException errors = binder.getErrors();
		if (!validation(request, tUser, view)) { //校验失败
			view.setViewName("preAddUser");		//跳转到修改页
			view.addObject("command",tUser);
		} else { //校验成功
			this.sampleService.saveUser(tUser);
			View v = new RedirectView(userInfoListKey);
			view.setView(v);
		}
		return view;
	}

	*//**
	 * 功能：删除用户信息<br>
	 *//*
	public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getLongParameter(request, userIdParam,0);
		this.sampleService.deleteUser(id);
		View v = new RedirectView(userInfoListKey);
		ModelAndView mav = new ModelAndView(v);
		return mav;
	}	
	
	*//**
	 * 对绑定的数据进行校验。
	 * 
	 * @param request HttpServlertRequest
	 * @param command FormBean Command
	 * @param modelAndView 如果校验失败，则将错误ExceptionModel绑定至该 ModelAndView 中
	 * @return 是否校验成功
	 *//*
	public boolean validation( HttpServletRequest request, Object command, ModelAndView modelAndView) {
		boolean isAllow = false;
		try {
			this.bind(request, command); //closeNoCatch() So if your Validator returns any Error, closeNoCatch will throw a ServletRequestBindingException
			isAllow = true;
		} catch (ServletRequestBindingException bindingException) {//校验失败将抛出该异常
			BindException bindException = (BindException) bindingException.getRootCause();
			modelAndView.addAllObjects(bindException.getModel());
		} catch(Exception ex){
			//LOG.error("Validation Be Throws Exception.");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return isAllow;
	}*/
}
