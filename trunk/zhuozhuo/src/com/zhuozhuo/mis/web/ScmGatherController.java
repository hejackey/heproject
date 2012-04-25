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
import com.zhuozhuo.mis.po.ScmGather;
import com.zhuozhuo.mis.po.ScmGatherDetail;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class ScmGatherController extends MultiActionController {
	private final String SAVE_PAGE="/gather/preAddScmGather";
	private final String LIST_PAGE="/gather/listScmGather";
	private ServiceFacotryUtil serviceFactoryUtil;
	private Logger log = Logger.getLogger(ScmGatherController.class);
	
	public ModelAndView listScmGather(HttpServletRequest request,HttpServletResponse response,
			ScmGather o){
		try{
			Object object = request.getSession().getAttribute("Q_STOCK_ORDER");
			if(!"navigate".equals(request.getParameter("type")) && object!=null){
				o = (ScmGather)object;
			}
			if(StringUtils.isEmpty(o.getUserid()))
				o.setUserid("0");
			if(!StringUtils.isEmpty(o.getProductName())||!StringUtils.isEmpty(o.getProductType())){
				o.setProdCond("0");
			}
			
			PageWraper pw = this.serviceFactoryUtil.getScmGatherService().listScmGather(o);
			
			if(pw!=null){
				o.setScmGatherList(pw.getResult());
			}
			o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
			
			request.getSession().removeAttribute("Q_STOCK_ORDER");
			return new ModelAndView(LIST_PAGE,"model",o);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("listScmGather exception==========="+e.getMessage());
			
			return new ModelAndView("dateAccessFail");
		}
	}
	
	public ModelAndView getDetailBySheetIdScmGather(HttpServletRequest request,HttpServletResponse response){
		try{
			String sheetid = request.getParameter("id");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			
			if(StringUtils.isEmpty(sheetid)){
				log.error("getDetailBySheetIdScmGather error param is null");
				pw.write("");
				
				return null;
			}
			
			List<ScmGatherDetail> detailList = this.serviceFactoryUtil.getScmGatherDetailService().getScmGatherDetailBySheetId(sheetid);
			Type listType = new TypeToken<List<ScmGatherDetail>>() {}.getType();
			
			pw.write(GsonUtil.toGson(detailList,listType));
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			log.error("getDetailBySheetIdScmGather exception==========="+e.getMessage());
			
			return null;
		}
	}
	public ModelAndView preAddScmGather(HttpServletRequest request,HttpServletResponse response,
			ScmGather o){
		try {
			request.getSession().setAttribute("Q_STOCK_ORDER", o);
			
			if(!StringUtils.isEmpty(o.getId())){
				o = this.serviceFactoryUtil.getScmGatherService().getScmGather(o.getId());
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
			log.error("preAddScmGather exception==========="+e.getMessage());
			
			return new ModelAndView("dateAccessFail");
		}
		
	}
	
	public ModelAndView saveScmGather(HttpServletRequest request,HttpServletResponse response,
			ScmGather o){
		try{
			o.setSheetstate(2);		//待审核状态
			o.setMakerid("2");	//制单人
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			o.setSheetid("CGD"+sdf.format(new Date()));
			o.setTransmodecode("");	//传送模式
			
			
			String result = this.serviceFactoryUtil.getScmGatherService().saveScmGather(o);
			if(!StringUtils.isEmpty(result)&& Integer.parseInt(result)>0){
				o.setResult("1");
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				QueryProvider qp = new QueryProvider();
				o.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
				o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
				
				o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
				o.setSheetid(o.getSheetid()+result);
				o.setScmGatherDetailList(this.serviceFactoryUtil.getScmGatherDetailService().getScmGatherDetailBySheetId(result));
			}
			
			return new ModelAndView(this.SAVE_PAGE,"model",o); 
		}
		catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("dataAccessFail");
		}
	}
	
	public ModelAndView editScmGather(HttpServletRequest request,HttpServletResponse response,
			ScmGather o){
		try{			
			int result = this.serviceFactoryUtil.getScmGatherService().editScmGather(o);
			if(result>0){
				o.setResult("2");
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				QueryProvider qp = new QueryProvider();
				o.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
				o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
				o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
				
				o.setScmGatherDetailList(this.serviceFactoryUtil.getScmGatherDetailService().getScmGatherDetailBySheetId(o.getId()));
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
	public ModelAndView updateStatusScmGather(HttpServletRequest request,
			HttpServletResponse response,ScmGather ox){
		try{					
			if(ox.getAid()!=null&&!StringUtils.isEmpty(request.getParameter("flag"))){
				String[] idArray = ox.getAid();												
				
				this.serviceFactoryUtil.getScmGatherService().updateStatusScmGather(idArray,Integer.parseInt(request.getParameter("flag")));
				
				request.getRequestDispatcher("listScmGather.do?result=1").forward(request, response);						
				return null;
			}
			else{
				log.error("updateStatusScmGather error parameter is null");
				return new ModelAndView("/dataAccessFailure");			
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("updateStatusScmGather exception");
			
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
