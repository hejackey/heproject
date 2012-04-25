package com.zhuozhuo.mis.web;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.reflect.TypeToken;
import com.zhuozhuo.mis.common.BaseMultiActionController;
import com.zhuozhuo.mis.po.ScmBarnType;
import com.zhuozhuo.mis.po.ScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageOutAndDetail;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;
import com.zhuozhuo.mis.po.ScmStorageOutQO;
import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.service.ScmBarnTypeService;
import com.zhuozhuo.mis.service.ScmStorageOutService;
import com.zhuozhuo.mis.service.TAdmDeptService;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.StringUtils;

/**
 * 操作发货单主表SCM_STORAGE_OUT
 * 
 * @author Administrator
 * 
 */
public class ScmStorageOutController extends BaseMultiActionController {
	private static final String scmStorageOutListView = "scmStorageOut/listScmStorageOuts";
	private static final String scmStorageOutFormView = "scmStorageOut/scmStorageOutForm";
	private static final String editScmStorageOutForm = "scmStorageOut/editScmStorageOutForm";
	private static final String listScmStorageOuts = "listScmStorageOut.do";
	private static final String queryScmStorageOutView = "scmStorageOut/queryScmStorageOut";// 综合查询页面
	private static final String scmStorageOutDetailView = "scmStorageOut/scmStorageOutDetail";
	private Logger log = Logger.getLogger(StockOrderController.class);
	private ScmStorageOutService scmStorageOutService;	
	private TAdmDeptService tadmDeptService;	
	private ScmBarnTypeService scmBarnTypeService;
	
	private List<ScmBarnType> getBarnTypeList()throws Exception{
		return scmBarnTypeService.getBarnTypeList();
	}
	private List<TAdmDept> getDeptList() throws Exception{
		return tadmDeptService.getDeptList();
	}
	
	/**
	 * 列出发货单主表SCM_STORAGE_OUT数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listScmStorageOuts(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ScmStorageOutQO scmStorageOutQO=new ScmStorageOutQO();
		bind(request, scmStorageOutQO);
		List<ScmBarnType> barnTypeList=getBarnTypeList();
		List<ScmStorageOut> list;
		if((scmStorageOutQO.getBarnId()==null)&&(scmStorageOutQO.getClientId()==null)&&(scmStorageOutQO.getSheetId()==null)&&(scmStorageOutQO.getSrcSheetId()==null)){
			list = scmStorageOutService.listScmStorageOuts();
		}else{
			list=scmStorageOutService.listScmStorageOuts(scmStorageOutQO);
		}	
		 
		Map map=new HashMap();
		map.put("barnTypeList", barnTypeList);
		map.put("listScmStorageOuts",list);
		ModelAndView mv = new ModelAndView(scmStorageOutListView, map);
		return mv;
		
		
	}
	
	//得到对应主表id的明细表的记录
	public ModelAndView showScmStorageOutDetail(HttpServletRequest request,HttpServletResponse response){
		try{
			String id = request.getParameter("id");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			List<ScmStorageOutDetail> scmStorageOutDetails= scmStorageOutService.getDetailScmStorageOut(id);
			Type listType = new TypeToken<List<ScmStorageOutDetail>>() {}.getType();
			pw.write(GsonUtil.toGson(scmStorageOutDetails,listType));			
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * 审核并流转
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView auditScmStorageOut(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		String[] ids=request.getParameterValues("id");
		//在这里进行审核并流转
		scmStorageOutService.auditScmStorageOut(ids);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageOuts);
		mv.setView(v);
		return mv;
	}
	
	public ModelAndView getDetailScmStorageOut(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");		
		ScmStorageOut storageOut = this.scmStorageOutService.getScmStorageOut(id);
		List<TAdmDept> deptList= getDeptList();
		List<ScmBarnType> barnTypeList= getBarnTypeList();
		List<ScmStorageOutDetail> list=scmStorageOutService.getDetailScmStorageOut(id);
		
		Map map=new HashMap();
		map.put("scmStorageOutDetails",list);
		map.put("deptList", deptList);
		map.put("barnTypeList",barnTypeList);	
		map.put("storageOut", storageOut);
		
		ModelAndView mv = new ModelAndView(scmStorageOutFormView,map);
		return mv;
	}
	
	/**
	 * 增加发货单主表SCM_STORAGE_OUT一条数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addScmStorageOut(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<TAdmDept> list= getDeptList();
		List<ScmBarnType> barnTypeList= getBarnTypeList();
		Map map=new HashMap();
		map.put("deptList", list);
		map.put("barnTypeList",barnTypeList);		
		ModelAndView mv = new ModelAndView(scmStorageOutFormView,map);
		
		return mv;
	}
	
	/**
	 * 保存发货单主表SCM_STORAGE_OUT一条数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveScmStorageOut(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		ScmStorageOutAndDetail scmStorageOutAndDetail=new ScmStorageOutAndDetail();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(scmStorageOutAndDetail);
		binder.bind(request);//进行绑定		
		scmStorageOutService.saveScmStorageOutAndDetail(scmStorageOutAndDetail);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageOuts);
		mv.setView(v);
		return mv;
	}
	
	/**
	 * 修改发货单主表SCM_STORAGE_OUT一条数据和明细表的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*public ModelAndView preEditScmStorageOut(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		String id=request.getParameter("id");		
		//根据id得到主表的数据和明细表的数据，然后转向修改页面
		Map map=new HashMap();
		ScmStorageOut scmStorageOut=new ScmStorageOut();
		map.put("scmStorageOut",scmStorageOut);
		List<ScmStorageOutDetail> scmStorageOutDetail=new ArrayList();
		map.put("scmStorageOutDetails",scmStorageOutDetail);
		
		ModelAndView mv = new ModelAndView(editScmStorageOutForm,map);
		return mv;
	}	*/

	/**
	 * 更新发货单主表SCM_STORAGE_OUT一条数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateScmStorageOut(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	/**
	 * 删除发货单主表SCM_STORAGE_OUT一条货多条数据以及明细表的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteScmStorageOuts(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String[] ids=request.getParameterValues("id");		
		scmStorageOutService.deleteScmStorageOuts(ids);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageOuts);
		mv.setView(v);
		return mv;
	}
	
	public ModelAndView editScmStorageOut(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		ScmStorageOutAndDetail scmStorageOutAndDetail=new ScmStorageOutAndDetail();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(scmStorageOutAndDetail);
		binder.bind(request);//进行绑定		
		scmStorageOutService.editScmStorageOut(scmStorageOutAndDetail);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageOuts);
		mv.setView(v);
		return mv;
	}

	public ScmStorageOutService getScmStorageOutService() {
		return scmStorageOutService;
	}

	public void setScmStorageOutService(
			ScmStorageOutService scmStorageOutService) {
		this.scmStorageOutService = scmStorageOutService;
	}

	public TAdmDeptService getTadmDeptService() {
		return tadmDeptService;
	}

	public void setTadmDeptService(TAdmDeptService tadmDeptService) {
		this.tadmDeptService = tadmDeptService;
	}
	public ScmBarnTypeService getScmBarnTypeService() {
		return scmBarnTypeService;
	}
	public void setScmBarnTypeService(ScmBarnTypeService scmBarnTypeService) {
		this.scmBarnTypeService = scmBarnTypeService;
	}
}
