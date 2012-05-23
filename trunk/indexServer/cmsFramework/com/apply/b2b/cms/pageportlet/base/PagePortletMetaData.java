package com.apply.b2b.cms.pageportlet.base;

public final class PagePortletMetaData {
    private final String prjName;
    private final String pagePortletName;
	
    private final String pagePortletType;
    
    private final String PAGEPORTLET_INLINE_TYPE = "inLine";
    private final String PAGEPORTLET_LINKED_TYPE = "linked";
    
    public PagePortletMetaData(String prjName, String ppName){
    	this.prjName = prjName;
    	this.pagePortletName = ppName;
    	this.pagePortletType = PAGEPORTLET_INLINE_TYPE;
    }
    
    public PagePortletMetaData(String prjName, String ppName, String type){
    	this.prjName = prjName;
    	this.pagePortletName = ppName;
    	this.pagePortletType = type;
    }
    
    public String getPrjName() {
		return prjName;
	}
    
	public String getPagePortletName() {
		return pagePortletName;
	}


	public String getPagePortletType() {
		return pagePortletType;
	}
	
	
}
