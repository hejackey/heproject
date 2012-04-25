package com.zhuozhuo.mis.web;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.zhuozhuo.mis.model.Provider;
import com.zhuozhuo.mis.po.ScmBarnType;
import com.zhuozhuo.mis.po.ScmPay;
import com.zhuozhuo.mis.po.ScmPayAndDetail;
import com.zhuozhuo.mis.po.ScmStorageIn;
import com.zhuozhuo.mis.po.ScmStorageInAndDetail;
import com.zhuozhuo.mis.po.ScmStorageInDetail;
import com.zhuozhuo.mis.po.ScmStorageInQ;
import com.zhuozhuo.mis.po.ScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;
import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.service.ProviderService;
import com.zhuozhuo.mis.service.ScmBarnTypeService;
import com.zhuozhuo.mis.service.ScmPayService;
import com.zhuozhuo.mis.service.ScmStorageInService;
import com.zhuozhuo.mis.service.TAdmDeptService;
import com.zhuozhuo.mis.service.TAdmUserService;
import com.zhuozhuo.utils.GsonUtil;

public class ScmStorageInController extends BaseMultiActionController {
	private static final String scmStorageInListView = "scmStorageIn/listScmStorageIns";//入库单list
	private static final String scmStorageInFormView = "scmStorageIn/scmStorageInForm";//入库单form
	private static final String scmStorageInFormView1 = "scmStorageIn/scmStorageInForm1";//退货单form
	private static final String editScmStorageInForm = "scmStorageIn/editScmStorageIn";
	private static final String editScmStorageInForm1 = "scmStorageIn/editScmStorageIn1";
	private static final String listScmStorageIns = "listScmStorageIn.do";
	private static final String ENTERWAREHOUSE = "enterWarehouse";//入库单
	private static final String RETURNEDPURCHASE = "returnedPurchase";//退货单
	private ScmStorageInService scmStorageInService;	
	private TAdmDeptService tadmDeptService;
	private ScmBarnTypeService scmBarnTypeService;
	private TAdmUserService tadmUserService;
	private ProviderService providerService;
	
	private List<ScmBarnType> getBarnTypeList()throws Exception{
		return scmBarnTypeService.getBarnTypeList();
	}
	
	private List<TAdmDept> getDeptList() throws Exception{
		return tadmDeptService.getDeptList();
	}
	
	private List<TAdmUser> getUserList()throws Exception{
		return tadmUserService.getUserList();
	}
	
	
	/**
	 * 审核退货单
	 */
	public ModelAndView auditTHD(HttpServletRequest request,HttpServletResponse response){
		
		String type=RETURNEDPURCHASE;			
		String[] ids=request.getParameterValues("id");
		//在这里进行审核并流转
		scmStorageInService.auditTHD(ids);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageIns+"?type="+type);
		mv.setView(v);
		return mv;
	}
	
	//得到id对应明细表的记录
	public ModelAndView showDetailScmStorageIn(HttpServletRequest request,HttpServletResponse response){
		try{
			String id = request.getParameter("id");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			List<ScmStorageInDetail> scmStorageInDetails= scmStorageInService.showDetailScmStorageIn(id);
			Type listType = new TypeToken<List<ScmStorageInDetail>>() {}.getType();
			pw.write(GsonUtil.toGson(scmStorageInDetails,listType));				
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
	public ModelAndView auditScmScmStorageIns(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String type=ENTERWAREHOUSE;			
		String[] ids=request.getParameterValues("id");
		//在这里进行审核并流转
		scmStorageInService.auditScmScmStorageIns(ids);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageIns+"?type="+type);
		mv.setView(v);
		return mv;
	}

	public ModelAndView listScmStorageIns(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String type=request.getParameter("type");			
		if(type==null) type=ENTERWAREHOUSE;
		List<TAdmDept> deptList= getDeptList();//得到部门列表
		ScmStorageInQ scmStorageInQ=new ScmStorageInQ();
		bind(request, scmStorageInQ);
		List<ScmStorageIn> list=null;
		if((scmStorageInQ.getDepartmentId()==null)&&(scmStorageInQ.getEndTime()==null)&&(scmStorageInQ.getSheetId()==null)&&(scmStorageInQ.getStartTime()==null)){
			list = scmStorageInService.listScmStorageIns(type);//没有输入条件
		}else{
			list = scmStorageInService.listScmStorageIns(type,scmStorageInQ);//有输入条件
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if((scmStorageInQ.getStartTime()==null)||(scmStorageInQ.getStartTime().equals(""))){
			scmStorageInQ.setStartTime(sdf.format(new Date()));
		}
		
		if((scmStorageInQ.getEndTime()==null)||(scmStorageInQ.getEndTime().equals(""))){
			scmStorageInQ.setEndTime(sdf.format(new Date()));
		}
		
		Map map=new HashMap();
		map.put("type",type);
		map.put("deptList",deptList);
		map.put("scmStorageInQ",scmStorageInQ);
		map.put("listScmStorageIns", list);
		ModelAndView mv = new ModelAndView(scmStorageInListView,map);
		return mv;
	}

	/**
	 * 增加一个入库单和明细表内容
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addScmStorageIn(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<TAdmDept> deptList= getDeptList();
		List<ScmBarnType> barnTypeList= getBarnTypeList();
		List<TAdmUser> userList=getUserList();		
		String type=request.getParameter("type");		
		if(type==null) type=ENTERWAREHOUSE;
		ModelAndView mv ;
		Map map=new HashMap();
		if(type.equals(ENTERWAREHOUSE)){
			map.put("type", type);
			map.put("deptList",deptList);
			map.put("barnTypeList",barnTypeList);
			map.put("userList",userList);
			mv = new ModelAndView(scmStorageInFormView,map);
			//mv = new ModelAndView(scmStorageInFormView,"type",type);
		}else{
			map.put("type", type);
			map.put("deptList",deptList);
			map.put("barnTypeList",barnTypeList);
			map.put("userList",userList);
			mv = new ModelAndView(scmStorageInFormView1,map);
			//mv = new ModelAndView(scmStorageInFormView1,"type",type);
		}		
		return mv;		
	}
	
	/**
	 * 保存一个入库单和明细表内容
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveScmStorageIn(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String type=request.getParameter("type");		
		if(type==null) type=ENTERWAREHOUSE;
		ScmStorageInAndDetail scmStorageInAndDetail=new ScmStorageInAndDetail();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(scmStorageInAndDetail);
		binder.bind(request);//进行绑定	
		if(type.equals(ENTERWAREHOUSE)){//入库单
			scmStorageInService.saveScmStorageInAndDetail(scmStorageInAndDetail,type);
		}else{//退货单
			scmStorageInService.saveScmStorageInAndDetail(scmStorageInAndDetail,type);
		}
		
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageIns+"?type="+type);
		mv.setView(v);
		return mv;
	}	

	public ModelAndView deleteScmScmStorageIns(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String type=request.getParameter("type");
		if(type==null) type=ENTERWAREHOUSE;
		String[] ids=request.getParameterValues("id");
		scmStorageInService.deleteScmScmStorageIns(ids);
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageIns+"?type="+type);
		mv.setView(v);
		return mv;
	}
	
	/**
	 * 编辑明细及主表的内容
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView editScmStorageIn(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");		
		String type=request.getParameter("type");
		if(type==null)
			type="enterWarehouse";
		ModelAndView mv = new ModelAndView();
		Map map=new HashMap();
		//准备数据
		ScmStorageIn scmStorageIn=scmStorageInService.getScmStorageInById(id,type);//入库单和退货单		
		List<ScmStorageInDetail> scmStorageInDetailList= scmStorageInService.getEditScmStorageInDetailByMasterId(id,type);//入库单和退货单
		List<TAdmDept> deptList= getDeptList();
		List<ScmBarnType> barnTypeList= getBarnTypeList();
		List<TAdmUser> userList=getUserList();
		String providerid=scmStorageIn.getProviderid();
		Provider provider=providerService.getProvider(providerid);
		String providerName=provider.getProviderName();
		map.put("type",type);
		map.put("scmStorageIn",scmStorageIn);
		map.put("scmStorageInDetailList",scmStorageInDetailList);
		map.put("deptList",deptList);
		map.put("barnTypeList",barnTypeList);
		map.put("userList",userList);
		map.put("providerName",providerName);		
		if(type.equals("enterWarehouse")){//入库单
			mv = new ModelAndView(editScmStorageInForm,map);
		}else{//退货单
			mv = new ModelAndView(editScmStorageInForm1,map);
		}		
		return mv;
	}

	/**
	 * 更新表SCM_STORAGE_IN和明细表SCM_STORAGE_IN_DETAIL
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateScmScmStorageIn(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		String sumQty=request.getParameter("sumQty");
		System.out.println(sumQty);
		String type=request.getParameter("type");//区分是入库单还是退货单		
		if(type==null) type=ENTERWAREHOUSE;
		ScmStorageInAndDetail scmStorageInAndDetail=new ScmStorageInAndDetail();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(scmStorageInAndDetail);
		binder.bind(request);//进行绑定		
		scmStorageInService.updateScmScmStorageIn(scmStorageInAndDetail,type);		
		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listScmStorageIns+"?type="+type);
		mv.setView(v);
		return mv;
	}

	public ScmStorageInService getScmStorageInService() {
		return scmStorageInService;
	}

	public void setScmStorageInService(ScmStorageInService scmStorageInService) {
		this.scmStorageInService = scmStorageInService;
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

	public TAdmUserService getTadmUserService() {
		return tadmUserService;
	}

	public void setTadmUserService(TAdmUserService tadmUserService) {
		this.tadmUserService = tadmUserService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

}
