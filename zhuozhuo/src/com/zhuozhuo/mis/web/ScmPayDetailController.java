package com.zhuozhuo.mis.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.zhuozhuo.mis.common.BaseMultiActionController;
import com.zhuozhuo.mis.po.ScmPayDetail;
import com.zhuozhuo.mis.service.ScmPayDetailService;
/**
 * 操作付款单明细表SCM_PAY_DETAIL
 * @author Administrator
 *
 */
public class ScmPayDetailController extends BaseMultiActionController{
	private ScmPayDetailService scmPayDetailService;
	/**
	 * 列出付款单明细表SCM_PAY_DETAIL数据(测试用)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listScmPayDetails(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		List<ScmPayDetail> list=scmPayDetailService.listScmPayDetails();		
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	/**
	 * 增加付款单明细表SCM_PAY_DETAIL一条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addScmPayDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		List<ScmPayDetail> list=scmPayDetailService.listScmPayDetails();
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	/**
	 * 修改付款单明细表SCM_PAY_DETAIL一条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView editScmPayDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		List<ScmPayDetail> list=scmPayDetailService.listScmPayDetails();
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	/**
	 * 保存付款单明细表SCM_PAY_DETAIL一条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveScmPayDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		List<ScmPayDetail> list=scmPayDetailService.listScmPayDetails();
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	/**
	 * 更新付款单明细表SCM_PAY_DETAIL一条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateScmPayDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		List<ScmPayDetail> list=scmPayDetailService.listScmPayDetails();
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	/**
	 * 删除付款单明细表SCM_PAY_DETAIL一条数据或多条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteScmPayDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		List<ScmPayDetail> list=scmPayDetailService.listScmPayDetails();
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	public ScmPayDetailService getScmPayDetailService() {
		return scmPayDetailService;
	}

	public void setScmPayDetailService(ScmPayDetailService scmPayDetailService) {
		this.scmPayDetailService = scmPayDetailService;
	}
}
