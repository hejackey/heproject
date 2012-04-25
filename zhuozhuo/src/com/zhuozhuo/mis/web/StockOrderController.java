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
import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.ExcelUtil;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class StockOrderController extends MultiActionController {
	private final String SAVE_PAGE="/stock/preAddStockOrder";
	private final String LIST_PAGE="/stock/listStockOrder";
	private final String LIST_URL="listStockOrder.do";	
	private ServiceFacotryUtil serviceFactoryUtil;
	private Logger log = Logger.getLogger(StockOrderController.class);
	
	public ModelAndView listStockOrder(HttpServletRequest request,HttpServletResponse response,
			ScmStockOrder o){
		try{
			Object object = request.getSession().getAttribute("Q_STOCK_ORDER");
			if(!"navigate".equals(request.getParameter("type")) && object!=null){
				o = (ScmStockOrder)object;
			}
			if(StringUtils.isEmpty(o.getUserid()))
				o.setUserid("0");
			if(!StringUtils.isEmpty(o.getProductName())||!StringUtils.isEmpty(o.getProductType())){
				o.setProdCond("0");
			}
			
			PageWraper pw = this.serviceFactoryUtil.getStockOrderService().listStockOrder(o);
			
			if(pw!=null){
				o.setStockOrderList(pw.getResult());
			}
			o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
			
			request.getSession().removeAttribute("Q_STOCK_ORDER");
			return new ModelAndView(LIST_PAGE,"model",o);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("listStockOrder exception==========="+e.getMessage());
			
			return new ModelAndView("dateAccessFail");
		}
	}
	
	public ModelAndView getDetailBySheetIdStockOrder(HttpServletRequest request,HttpServletResponse response){
		try{
			String sheetid = request.getParameter("id");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			
			if(StringUtils.isEmpty(sheetid)){
				log.error("getDetailBySheetIdStockOrder error param is null");
				pw.write("");
				
				return null;
			}
			
			List<ScmStockOrderDetail> detailList = this.serviceFactoryUtil.getStockOrderDetailService().getStockOrderDetailBySheetId(sheetid);
			Type listType = new TypeToken<List<ScmStockOrderDetail>>() {}.getType();
			
			pw.write(GsonUtil.toGson(detailList,listType));
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			log.error("getDetailBySheetIdStockOrder exception==========="+e.getMessage());
			
			return null;
		}
	}
	public ModelAndView preAddStockOrder(HttpServletRequest request,HttpServletResponse response,
			ScmStockOrder o){
		try {
			request.getSession().setAttribute("Q_STOCK_ORDER", o);
			
			if(!StringUtils.isEmpty(o.getId())){
				o = this.serviceFactoryUtil.getStockOrderService().getStockOrder(o.getId());
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
			log.error("preAddStockOrder exception==========="+e.getMessage());
			
			return new ModelAndView("dateAccessFail");
		}
		
	}
	
	public ModelAndView saveStockOrder(HttpServletRequest request,HttpServletResponse response,
			ScmStockOrder o){
		try{
			o.setSheetstate(2);		//待审核状态
			o.setMakerid("2");	//制单人
						
			o.setTransmodecode("");	//传送模式			
			
			String result = this.serviceFactoryUtil.getStockOrderService().saveStockOrder(o);
			if(!StringUtils.isEmpty(result)&& Integer.parseInt(result)>0){
				o.setResult("1");
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				QueryProvider qp = new QueryProvider();
				o.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
				o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
				
				o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
				o.setSheetid(o.getSheetid()+result);
				o.setStockOrderDetailList(this.serviceFactoryUtil.getStockOrderDetailService().getStockOrderDetailBySheetId(result));
			}
			
			//return new ModelAndView(this.SAVE_PAGE,"model",o);
			request.getRequestDispatcher(LIST_URL).forward(request, response);
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("dataAccessFail");
		}
	}
	
	public ModelAndView editStockOrder(HttpServletRequest request,HttpServletResponse response,
			ScmStockOrder o){
		try{			
			int result = this.serviceFactoryUtil.getStockOrderService().editStockOrder(o);
			if(result>0){
				o.setResult("2");
				o.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
				QueryProvider qp = new QueryProvider();
				o.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
				o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
				o.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
				
				o.setStockOrderDetailList(this.serviceFactoryUtil.getStockOrderDetailService().getStockOrderDetailBySheetId(o.getId()));
			}
			
			//return new ModelAndView(this.SAVE_PAGE,"model",o);
			
			request.getRequestDispatcher(LIST_URL).forward(request, response);
			return null;
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
	public ModelAndView updateStatusStockOrder(HttpServletRequest request,
			HttpServletResponse response,ScmStockOrder ox){
		try{					
			if(ox.getAid()!=null&&!StringUtils.isEmpty(request.getParameter("flag"))){
				String[] idArray = ox.getAid();												
				
				if("2".equals(request.getParameter("flag"))){
					//删除
					this.serviceFactoryUtil.getStockOrderService().deleteStockOrder(idArray);
				}
				else
					this.serviceFactoryUtil.getStockOrderService().updateStatusStockOrder(idArray,Integer.parseInt(request.getParameter("flag")));
				
				request.getRequestDispatcher("listStockOrder.do?result=1").forward(request, response);						
				return null;
			}
			else{
				log.error("updateStatusStockOrder error parameter is null");
				return new ModelAndView("/dataAccessFailure");			
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("updateStatusStockOrder exception");
			
			return new ModelAndView("/dataAccessFailure");
		}
	}
	
	public ModelAndView exportStockOrderToExcel(HttpServletRequest request,
			HttpServletResponse response,ScmStockOrder ox){
		try{
			ox.setStockOrderList(this.serviceFactoryUtil.getStockOrderService().exportStockOrderToExcel(ox));
			
			ExcelUtil.exportStockOrderToExcel(ox,response);
			
			return new ModelAndView(this.LIST_PAGE,"model",ox);
		}catch(Exception e){
			e.printStackTrace();
			log.error("ScmStorageController listExcelScmStorage exception ,e===="+e.getMessage());
			
			return new ModelAndView("dataAcessFailure");
		}
	}
	
	public ServiceFacotryUtil getServiceFactoryUtil() {
		return serviceFactoryUtil;
	}
	public void setServiceFactoryUtil(ServiceFacotryUtil serviceFactoryUtil) {
		this.serviceFactoryUtil = serviceFactoryUtil;
	}
}
