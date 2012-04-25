package com.zhuozhuo.mis.web;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.reflect.TypeToken;
import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.ScmDiaoBo;
import com.zhuozhuo.mis.po.ScmDiaoBoDetail;
import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class DiaoBoController extends MultiActionController {
	private final String SAVE_PAGE="/diaoBo/preAddDiaoBo";
	private final String LIST_PAGE="/diaoBo/listDiaoBo";
	private ServiceFacotryUtil serviceFactoryUtil;
	private Logger log = Logger.getLogger(DiaoBoController.class);
	
	public ModelAndView listDiaoBo(HttpServletRequest request,HttpServletResponse response,
			ScmDiaoBo o){
		try{
			Object object = request.getSession().getAttribute("Q_STOCK_ORDER");
			if(!"navigate".equals(request.getParameter("type")) && object!=null){
				o = (ScmDiaoBo)object;
			}
			if(StringUtils.isEmpty(o.getUserid()))
				o.setUserid("0");
			if(!StringUtils.isEmpty(o.getProductName())||!StringUtils.isEmpty(o.getProductType())){
				o.setProdCond("0");
			}
			
			PageWraper pw = this.serviceFactoryUtil.getDiaoBoService().listDiaoBo(o);
			
			if(pw!=null){
				o.setDiaoBoList(pw.getResult());
			}
			o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
			
			request.getSession().removeAttribute("Q_STOCK_ORDER");
			return new ModelAndView(LIST_PAGE,"model",o);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("listDiaoBo exception==========="+e.getMessage());
			
			return new ModelAndView("dateAccessFail");
		}
	}
	
	public ModelAndView getDetailBySheetIdDiaoBo(HttpServletRequest request,HttpServletResponse response){
		try{
			String sheetid = request.getParameter("id");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			
			if(StringUtils.isEmpty(sheetid)){
				log.error("getDetailBySheetIdDiaoBo error param is null");
				pw.write("");
				
				return null;
			}
			
			List<ScmDiaoBoDetail> detailList = this.serviceFactoryUtil.getDiaoBoDetailService().getDiaoBoDetailBySheetId(sheetid);
			Type listType = new TypeToken<List<ScmDiaoBoDetail>>() {}.getType();
			
			pw.write(GsonUtil.toGson(detailList,listType));
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			log.error("getDetailBySheetIdDiaoBo exception==========="+e.getMessage());
			
			return null;
		}
	}
	public ModelAndView preAddDiaoBo(HttpServletRequest request,HttpServletResponse response,
			ScmDiaoBo o){
		try {
			request.getSession().setAttribute("Q_STOCK_ORDER", o);
			
			if(!StringUtils.isEmpty(o.getId())){
				o = this.serviceFactoryUtil.getDiaoBoService().getDiaoBo(o.getId());
			}
			else{
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				QueryProvider qp = new QueryProvider();
				o.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
				o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
				o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
			}
			
			if(o.getStockopentype()==0)
				o.setStockopentype(1);
			
			
			return new ModelAndView(this.SAVE_PAGE,"model",o);
		} catch (Exception e) {			
			e.printStackTrace();
			log.error("preAddDiaoBo exception==========="+e.getMessage());
			
			return new ModelAndView("dateAccessFail");
		}
		
	}
	
	public ModelAndView saveDiaoBo(HttpServletRequest request,HttpServletResponse response,
			ScmDiaoBo o){
		try{
			o.setSheetstate(2);		//待审核状态
			o.setMakerid("2");	//制单人
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			o.setSheetid("CGD"+sdf.format(new Date()));
			o.setTransmodecode("");	//传送模式
			
			
			String result = this.serviceFactoryUtil.getDiaoBoService().saveDiaoBo(o);
			if(!StringUtils.isEmpty(result)&& Integer.parseInt(result)>0){
				o.setResult("1");
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				QueryProvider qp = new QueryProvider();
				o.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
				o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
				
				o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
				o.setSheetid(o.getSheetid()+result);
				o.setDiaoBoDetailList(this.serviceFactoryUtil.getDiaoBoDetailService().getDiaoBoDetailBySheetId(result));
			}
			
			return new ModelAndView(this.SAVE_PAGE,"model",o); 
		}
		catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("dataAccessFail");
		}
	}
	
	public ModelAndView editDiaoBo(HttpServletRequest request,HttpServletResponse response,
			ScmDiaoBo o){
		try{			
			int result = this.serviceFactoryUtil.getDiaoBoService().editDiaoBo(o);
			if(result>0){
				o.setResult("2");
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				QueryProvider qp = new QueryProvider();
				o.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
				o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
				o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
				
				o.setDiaoBoDetailList(this.serviceFactoryUtil.getDiaoBoDetailService().getDiaoBoDetailBySheetId(o.getId()));
			}
			
			return new ModelAndView(this.SAVE_PAGE,"model",o); 
		}
		catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("dataAccessFail");
		}
	}
	
	/**
	 * 修改部门状态
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateStatusDiaoBo(HttpServletRequest request,
			HttpServletResponse response,ScmDiaoBo ox){
		try{					
			if(ox.getAid()!=null&&!StringUtils.isEmpty(request.getParameter("flag"))){
				String[] idArray = ox.getAid();												
				
				this.serviceFactoryUtil.getDiaoBoService().updateStatusDiaoBo(idArray,Integer.parseInt(request.getParameter("flag")));
				
				request.getRequestDispatcher("listDiaoBo.do?result=1").forward(request, response);						
				return null;
			}
			else{
				log.error("updateStatusDiaoBo error parameter is null");
				return new ModelAndView("/dataAccessFailure");			
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("updateStatusDiaoBo exception");
			
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	public ServiceFacotryUtil getServiceFactoryUtil() {
		return serviceFactoryUtil;
	}
	public void setServiceFactoryUtil(ServiceFacotryUtil serviceFactoryUtil) {
		this.serviceFactoryUtil = serviceFactoryUtil;
	}
}
