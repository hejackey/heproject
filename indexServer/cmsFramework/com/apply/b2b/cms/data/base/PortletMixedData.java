package com.apply.b2b.cms.data.base;

import java.util.Date;

/**
 * 
 * @author luoweifeng
 *
 */

public abstract class PortletMixedData implements IPortletTextData, IPortletImageData, IHttp  {
	public String getHttpURL(){
		return "#";
	}
	
	public String getSummary(){
		return null;
	}
	
	public String getXSize(){
		return null;
	}
	
	public String getYSize(){
		return null;
	}
	
	public Date getCreateDate(){
		return null;
	}
	
	public Date getUpdateDate(){
		return null;
	}
	
	public String getOtherSummary(){
		return null;
	}
	
	public Object getContent(){
		return null;
	}
}