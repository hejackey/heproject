package com.zhuozhuo.mis.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zhuozhuo.mis.po.ScmBarnType;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class BarnTypeController extends MultiActionController {
	private Logger log = Logger.getLogger(BarnTypeController.class);
	private ServiceFacotryUtil serviceFactoryUtil;
	
	public ModelAndView getByIdBarnType(HttpServletRequest request,HttpServletResponse response){		
		try {
			String id =request.getParameter("id");
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			
			if(StringUtils.isEmpty(id)){
				log.error("getByIdBarnType param is err ,id is null");								
				pw.write("");
				
				return null;
			}
			
			ScmBarnType barnType = serviceFactoryUtil.getScmBarnTypeService().getBarnTypeById(id);
			pw.write(GsonUtil.toGson(barnType));
			
			return null;
		} catch (Exception e) {			
			e.printStackTrace();			
			log.error("getByIdBarnType exception");						
		}
		
		return null;
	}

	public ServiceFacotryUtil getServiceFactoryUtil() {
		return serviceFactoryUtil;
	}

	public void setServiceFactoryUtil(ServiceFacotryUtil serviceFactoryUtil) {
		this.serviceFactoryUtil = serviceFactoryUtil;
	}
}
