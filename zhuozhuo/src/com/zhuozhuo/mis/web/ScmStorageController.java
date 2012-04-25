package com.zhuozhuo.mis.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zhuozhuo.mis.po.report.ScmStorage;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.DateUtils;
import com.zhuozhuo.utils.ExcelUtil;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class ScmStorageController extends MultiActionController {
	private final String LIST_PAGE="report/scmstorage/storageReport";
	private final String BARN_TYPE_LIST_PAGE = "report/scmstorage/barnTypeStorageReport";
	private final String PRODUCT_TYPE_LIST_PAGE = "report/scmstorage/productTypeStorageReport";
	private Logger log = Logger.getLogger(ScmStorageController.class);
	private ServiceFacotryUtil serviceFactoryUtil;
	
	/**
	 * 进销存汇总报表
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView listScmStorage(HttpServletRequest request,
			HttpServletResponse response,ScmStorage o){
		try{
			o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
			o = this.serviceFactoryUtil.getScmStorageService().listScmStorageHj(o);
			o.setProductTypes(this.serviceFactoryUtil.getProductTypeService().getProdcutTypeSonId(o.getProductType()));
			
			PageWraper pw = this.serviceFactoryUtil.getScmStorageService().listScmStorage(o);			
			if(pw != null)
				o.setReportList(pw.getResult());
			
			return new ModelAndView(this.LIST_PAGE,"model",o);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("ScmStorageController listScmStorage exception ,e===="+e.getMessage());
			
			return new ModelAndView("dataAcessFailure");
		}
	}

	public ModelAndView listExcelScmStorage(HttpServletRequest request,
			HttpServletResponse response,ScmStorage o){
		try{
			o=this.serviceFactoryUtil.getScmStorageService().listScmStorageHj(o);
			o.setReportList(this.serviceFactoryUtil.getScmStorageService().listScmStorageToExcel(o));
			o.setProductTypes(this.serviceFactoryUtil.getProductTypeService().getProdcutTypeSonId(o.getProductType()));
			
			ExcelUtil.exportStoragetToExcel(o,response);
			
			return new ModelAndView(this.LIST_PAGE,"model",o);
		}catch(Exception e){
			e.printStackTrace();
			log.error("ScmStorageController listExcelScmStorage exception ,e===="+e.getMessage());
			
			return new ModelAndView("dataAcessFailure");
		}
	}	
	
	/**
	 * 库存类型汇总报表
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView listBarnTypeStorage(HttpServletRequest request,
			HttpServletResponse response,ScmStorage o){
		try{
			if(StringUtils.isEmpty(o.getStartTime())){
				if(StringUtils.isEmpty(o.getEndTime()))
					o.setStartTime(DateUtils.getDateFormat(DateUtils.getBeforeMonth(new Date()),"yyyy-MM-dd"));
				else{
					o.setStartTime(DateUtils.getDateFormat(DateUtils.getBeforeMonth(
							DateUtils.parseDateFormat(o.getEndTime(),"yyyy-MM-dd")),"yyyy-MM-dd"));
				}
			}
			if(StringUtils.isEmpty(o.getEndTime()))
				o.setEndTime(DateUtils.getDateFormat(new Date(),"yyyy-MM-dd"));
			
			o.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());			
			o.setBarnids(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeSonId(o.getBarnid()));
			
			PageWraper pw = this.serviceFactoryUtil.getScmStorageService().listBarnTypeStorage(o);			
			if(pw != null)
				o.setReportList(pw.getResult());
			
			return new ModelAndView(this.BARN_TYPE_LIST_PAGE,"model",o);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("ScmStorageController listBarnTypeStorage exception ,e===="+e.getMessage());
			
			return new ModelAndView("dataAcessFailure");
		}
	}	
	
	public ModelAndView listExcelBarnTypeStorage(HttpServletRequest request,
			HttpServletResponse response,ScmStorage o){
		try{
			if(StringUtils.isEmpty(o.getStartTime())){
				if(StringUtils.isEmpty(o.getEndTime()))
					o.setStartTime(DateUtils.getDateFormat(DateUtils.getBeforeMonth(new Date()),"yyyy-MM-dd"));
				else{
					o.setStartTime(DateUtils.getDateFormat(DateUtils.getBeforeMonth(
							DateUtils.parseDateFormat(o.getEndTime(),"yyyy-MM-dd")),"yyyy-MM-dd"));
				}
			}
			if(StringUtils.isEmpty(o.getEndTime()))
				o.setEndTime(DateUtils.getDateFormat(new Date(),"yyyy-MM-dd"));
			
			o.setBarnids(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeSonId(o.getBarnid()));
			o.setReportList(this.serviceFactoryUtil.getScmStorageService().listBarnTypeStorageToExcel(o));
			
			ExcelUtil.exportBarnTypeStorageToExcel(o,response);
			
			return new ModelAndView(this.BARN_TYPE_LIST_PAGE,"model",o);
		}catch(Exception e){
			e.printStackTrace();
			log.error("ScmStorageController listExcelBarnTypeStorage exception ,e===="+e.getMessage());
			
			return new ModelAndView("dataAcessFailure");
		}
	}	
		
	/**
	 * 商品类型汇总报表
	 * @param request
	 * @param response
	 * @param o
	 * @return
	 */
	public ModelAndView listProductTypeStorage(HttpServletRequest request,
			HttpServletResponse response,ScmStorage o){
		try{
			o.setProductTypes(this.serviceFactoryUtil.getProductTypeService().getProdcutTypeSonId(o.getProductType()));			
			o = this.serviceFactoryUtil.getScmStorageService().listProductTypeStorageHj(o);
			
			PageWraper pw = this.serviceFactoryUtil.getScmStorageService().listProductTypeStorage(o);			
			if(pw != null)
				o.setReportList(pw.getResult());
			
			return new ModelAndView(this.PRODUCT_TYPE_LIST_PAGE,"model",o);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("ScmStorageController listProductTypeStorage exception ,e===="+e.getMessage());
			
			return new ModelAndView("dataAcessFailure");
		}
	}
	
	public ModelAndView listExcelProductTypeStorage(HttpServletRequest request,
			HttpServletResponse response,ScmStorage o){
		try{
			//o.setProductTypes(this.serviceFactoryUtil.getProductTypeService().getProdcutTypeSonId(o.getProductType()));			
			o = this.serviceFactoryUtil.getScmStorageService().listProductTypeStorageHj(o);			
			o.setReportList(this.serviceFactoryUtil.getScmStorageService().listProductTypeStorageToExcel(o));			
			
			ExcelUtil.exportProductTypeStorageToExcel(o,response);
			
			return new ModelAndView(this.PRODUCT_TYPE_LIST_PAGE,"model",o);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("ScmStorageController listExcelProductTypeStorage exception ,e===="+e.getMessage());
			
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
