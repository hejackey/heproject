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
import com.zhuozhuo.mis.po.ScmBarnType;
import com.zhuozhuo.mis.service.ScmBarnTypeService;

public class ScmBarnTypeController extends BaseMultiActionController {
	private static final long scmBarnTypeRoot = 0;// 设置根目录的id为零
	private static final String parentName = "仓库类别";// 设置根目录的名称

	private static final String scmBarnTypeListView = "scmBarnType/listScmBarnTypes";
	private static final String scmBarnTypeFormView = "scmBarnType/scmBarnTypeForm";
	private static final String editScmBarnTypeForm = "scmBarnType/editScmBarnTypeForm";
	private static final String listScmBarnTypes = "listScmBarnType.do";

	private ScmBarnTypeService scmBarnTypeService;

	/**
	 * 查询所有的仓库类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listScmBarnTypes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		List scmBarnTypeList;
		ScmBarnType scmBarnType = new ScmBarnType();
		if (id == null) {// 如果点击的是根
			scmBarnTypeList = scmBarnTypeService.getAllScmBarnTypes();
			scmBarnType.setId("0");
			scmBarnType.setParentid(null);
			scmBarnType.setParentname(null);
		} else {
			scmBarnTypeList = scmBarnTypeService.getChildScmBarnTypes(id);
			scmBarnType = scmBarnTypeService.getScmBarnType(id);
		}

		Map map = new HashMap();
		map.put("scmBarnTypeList", scmBarnTypeList);
		List<ScmBarnType> list = scmBarnTypeService.getAllScmBarnTypes();
		map.put("scmBarnTypeTreeList", list);
		map.put("scmBarnType", scmBarnType);
		ModelAndView mv = new ModelAndView(scmBarnTypeListView, map);
		return mv;
	}

	/**
	 * 新增加一个仓库类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addScmBarnType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String parentid = request.getParameter("parentid");
		ModelAndView mv = new ModelAndView(scmBarnTypeFormView, "command",
				new ScmBarnType());
		return mv;
	}

	/**
	 * 保存一个仓库类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveScmBarnType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ScmBarnType scmBarnType = new ScmBarnType();
		ModelAndView mv = new ModelAndView();
		if (!validation(request, scmBarnType, mv)) { // 校验失败
			mv.setViewName(scmBarnTypeFormView);// 跳转到修改页
			mv.addObject("command", scmBarnType);
		} else { // 校验成功
			scmBarnTypeService.saveScmBarnType(scmBarnType);
			View v = new RedirectView(listScmBarnTypes);
			mv.setView(v);
		}
		return mv;
	}

	/**
	 * 修改一个仓库类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView editScmBarnType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ScmBarnType scmBarnType = scmBarnTypeService.getScmBarnType(id);
		ModelAndView mv = new ModelAndView(editScmBarnTypeForm, "command",
				scmBarnType);
		return mv;
	}

	/**
	 * 保存修改的仓库类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateScmBarnType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ScmBarnType scmBarnType = new ScmBarnType();
		ModelAndView view = new ModelAndView();
		if (!validation(request, scmBarnType, view)) { // 校验失败
			view.setViewName(editScmBarnTypeForm);// 跳转到修改页
			view.addObject("command", scmBarnType);
		} else { // 校验成功
			// 更新数据库
			scmBarnTypeService.updateScmBarnType(scmBarnType);
			View v = new RedirectView(listScmBarnTypes);
			view.setView(v);
		}
		return view;
	}

	/**
	 * 删除一个或多个仓库类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteScmBarnTypes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("ids");

		scmBarnTypeService.deleteScmBarnType(ids);
		View v = new RedirectView(listScmBarnTypes);
		ModelAndView mv = new ModelAndView(v);
		return mv;
	}

	public ScmBarnTypeService getScmBarnTypeService() {
		return scmBarnTypeService;
	}

	public void setScmBarnTypeService(ScmBarnTypeService scmBarnTypeService) {
		this.scmBarnTypeService = scmBarnTypeService;
	}
}
