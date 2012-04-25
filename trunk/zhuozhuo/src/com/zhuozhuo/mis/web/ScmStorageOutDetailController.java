package com.zhuozhuo.mis.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.zhuozhuo.mis.common.BaseMultiActionController;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;
import com.zhuozhuo.mis.service.ScmStorageOutDetailService;
/**
 * 操作发货单明细表SCM_STORAGE_OUT_DETAIL
 * @author Administrator
 *
 */
public class ScmStorageOutDetailController extends BaseMultiActionController {
	private ScmStorageOutDetailService scmStorageOutDetailService;
	/**
	 * 列出发货单明细表SCM_STORAGE_OUT_DETAIL数据(测试用)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listScmStorageOutDetails(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		List<ScmStorageOutDetail> list=scmStorageOutDetailService.listScmStorageOutDetails();
		ModelAndView mv =new ModelAndView();
		return mv;
	}
	/**
	 *  增加发货单明细表SCM_STORAGE_OUT_DETAIL一条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addScmStorageOutDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		ModelAndView mv =new ModelAndView();
		return mv;
	}
	/**
	 *  修改发货单明细表SCM_STORAGE_OUT_DETAIL一条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView editScmStorageOutDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		ModelAndView mv =new ModelAndView();
		return mv;
	}
	/**
	 * 保存发货单明细表SCM_STORAGE_OUT_DETAIL一条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveScmStorageOutDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv =new ModelAndView();
		return mv;
	}
	/**
	 * 更新发货单明细表SCM_STORAGE_OUT_DETAIL一条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateScmStorageOutDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv =new ModelAndView();
		return mv;
	}
	/**
	 * 删除发货单明细表SCM_STORAGE_OUT_DETAIL一条或多条数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteScmStorageOutDetails(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv =new ModelAndView();
		return mv;
	}	
	public ScmStorageOutDetailService getScmStorageOutDetailService() {
		return scmStorageOutDetailService;
	}
	public void setScmStorageOutDetailService(
			ScmStorageOutDetailService scmStorageOutDetailService) {
		this.scmStorageOutDetailService = scmStorageOutDetailService;
	}
}
