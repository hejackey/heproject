package com.apply.b2b.cms.data.base;

/**
 * 
 * @author luoweifeng
 *
 */

public abstract class PortletImageData  implements IPortletImageData, IHttp {
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
}
