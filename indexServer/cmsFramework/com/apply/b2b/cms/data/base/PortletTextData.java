package com.apply.b2b.cms.data.base;

import java.util.Date;



/**
 * 
 * @author luoweifeng
 *
 */

public abstract class PortletTextData implements IPortletTextData, IHttp {
	public String getHttpURL(){
		return "#";
	}
	
	public Date getCreateDate(){
		return null;
	}
	
	public Date getUpdateDate(){
		return null;
	}
	
	public String getSummary(){
		return "";
	}
	
	public String getOtherSummary(){
		return "";
	}
	
	public Object getContent(){
		return null;
	}
}