package com.zhuozhuo.mis.web;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.reflect.TypeToken;
import com.zhuozhuo.mis.common.BaseMultiActionController;
import com.zhuozhuo.mis.po.ScmPay;
import com.zhuozhuo.mis.po.ScmPayAndDetail;
import com.zhuozhuo.mis.po.ScmPayDetail;
import com.zhuozhuo.mis.po.ScmPayQ;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;
import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.service.ScmPayDetailService;
import com.zhuozhuo.mis.service.ScmPayService;
import com.zhuozhuo.mis.service.TAdmDeptService;
import com.zhuozhuo.mis.service.TAdmUserService;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.StringUtils;

/**
 * 操作付款单主表SCM_PAY
 * 
 * @author Administrator
 * 
 */
public class ScmPayController extends BaseMultiActionController {
	private static final String scmPayListView = "scmPay/listScmPays";
	private static final String scmPayFormView = "scmPay/scmPayForm";
	private static final String editscmPayForm = "scmPay/editScmPayForm";
	private static final String listScmPays = "listScmPay.do";
	private static final String queryScmPayView = "scmPay/queryScmPay";// 综合查询页面
	private ScmPayService scmPayService;
	private ScmPayDetailService scmPayDetailService;
	private TAdmDeptService tadmDeptService;
	private TAdmUserService tadmUserService;
	
	private List<TAdmDept> getDeptList() throws Exception{
		return tadmDeptService.getDeptList();
	}
	
	//得到id对应明细表的记录
	public ModelAndView showScmPayDetail(HttpServletRequest request,HttpServletResponse response){
		try{
			String id = request.getParameter("id");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			List<ScmPayDetail> scmPayDetails= scmPayService.showScmPayDetail(id);
			Type listType = new TypeToken<List<ScmPayDetail>>() {}.getType();
			pw.write(GsonUtil.toGson(scmPayDetails,listType));			
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 增加付款单主表SCM_PAY一条数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addScmPay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<TAdmDept> list= getDeptList();
		ModelAndView mv = new ModelAndView(scmPayFormView,"deptList",list);		
		return mv;
	}
	
	private List<TAdmUser> getUserList()throws Exception{
		return tadmUserService.getUserList();
	}

	/**
	 * 列出付款单主表SCM_PAY一条数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listScmPays(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		ScmPayQ scmPayQ=new ScmPayQ();
		bind(request, scmPayQ);
		List<ScmPay> list;
		List<TAdmUser> userList=getUserList();
		if((scmPayQ.getCheckCode()==null)&&(scmPayQ.getSheetId()==null)&&(scmPayQ.getUserId()==null) || scmPayQ.getUserId().equals("0") ){
			list = scmPayService.listScmPays();
		}else{
			list = scmPayService.listScmPays(scmPayQ);
		}
		Map map=new HashMap();
		map.put("userList",userList);
		map.put("listScmPays",list);
		ModelAndView mv = new ModelAndView(scmPayListView,map);
		return mv;
	}	

	public ModelAndView preEditScmPay(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		String id=request.getParameter("id");
		//根据id得到主表和明细表的数据，并转向修改页面		
		Map map=new HashMap();
		ScmPay scmPay= this.scmPayService.getScmPay(id);
		List<TAdmDept> list= getDeptList();
		List<ScmPayDetail> scmPayDetailList=this.scmPayDetailService.getScmPayDetailByMasterId(id);
		
		map.put("scmPay",scmPay);		
		map.put("scmPayDetailList",scmPayDetailList);
		map.put("deptList", list);
		
		ModelAndView mv = new ModelAndView(scmPayFormView,map);
		return mv;
	}
	
	/**
	 * 修改付款单主表SCM_PAY一条数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView editScmPay(HttpServletRequest request,HttpServletResponse response) throws Exception {				
		ScmPayAndDetail scmPayAndDetail=new ScmPayAndDetail();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(scmPayAndDetail);
		binder.bind(request);//进行绑定		
		scmPayService.editScmPayAndDetail(scmPayAndDetail);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmPays);
		mv.setView(v);
		return mv;
	}

	/**
	 * 保存付款单主表SCM_PAY一条数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveScmPay(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		ScmPayAndDetail scmPayAndDetail=new ScmPayAndDetail();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(scmPayAndDetail);
		binder.bind(request);//进行绑定		
		scmPayService.saveScmPayAndDetail(scmPayAndDetail);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmPays);
		mv.setView(v);
		return mv;
	}

	/**
	 * 更新付款单主表SCM_PAY一条数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateScmPay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	/**
	 * 删除付款单主表SCM_PAY一条数据或多条数据及其明细表的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteScmPays(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String[] ids=request.getParameterValues("id");		
		scmPayService.deleteScmPays(ids);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmPays);
		mv.setView(v);
		return mv;
	}

	public ModelAndView getScmPayDetailByProviderId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try{
			String providerid = request.getParameter("providerid");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			
			if(StringUtils.isEmpty(providerid)){
				
				pw.write("");
				
				return null;
			}
			
			List<ScmPayDetail> detailList = this.scmPayDetailService.getScmPayDetailByProviderId(providerid);
			Type listType = new TypeToken<List<ScmPayDetail>>() {}.getType();
			
			pw.write(GsonUtil.toGson(detailList,listType));
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			
			return null;
		}
	}
	public ScmPayService getScmPayService() {
		return scmPayService;
	}

	public void setScmPayService(ScmPayService scmPayService) {
		this.scmPayService = scmPayService;
	}

	public TAdmDeptService getTadmDeptService() {
		return tadmDeptService;
	}

	public void setTadmDeptService(TAdmDeptService tadmDeptService) {
		this.tadmDeptService = tadmDeptService;
	}

	public ScmPayDetailService getScmPayDetailService() {
		return scmPayDetailService;
	}

	public void setScmPayDetailService(ScmPayDetailService scmPayDetailService) {
		this.scmPayDetailService = scmPayDetailService;
	}

	public TAdmUserService getTadmUserService() {
		return tadmUserService;
	}

	public void setTadmUserService(TAdmUserService tadmUserService) {
		this.tadmUserService = tadmUserService;
	}
}
