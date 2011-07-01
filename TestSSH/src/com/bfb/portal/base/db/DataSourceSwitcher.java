package com.bfb.portal.base.db;


import org.springframework.util.Assert;

public class DataSourceSwitcher {
	@SuppressWarnings("unchecked")
	private static final ThreadLocal contextHolder = new ThreadLocal();

	@SuppressWarnings("unchecked")
	public static void setDataSource(String dataSource) {
		Assert.notNull(dataSource, "dataSource cannot be null");
		contextHolder.set(dataSource);
	}

	public static void setMaster(){
		clearDataSource();
		//setDataSource("master");
    }
	
	public static void setSlave() {
		setDataSource("slave");
	}
	
	public static String getDataSource() {
		Object o = contextHolder.get();
		if(o==null)
			return "slave";
		return (String) o;
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}


