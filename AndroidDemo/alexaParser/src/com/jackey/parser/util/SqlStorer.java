package com.jackey.parser.util;

/**
 * <p>Title: SDK For ZLWT Wap</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: zlwt</p>
 * @author pym
 * @version 2.0
 */

public class SqlStorer {
	
	public static final String SQL_WEBSORTINFO_INSERT = "insert into web_sort_info(title,domainname,sort,description,sorttype,webtype,createtime) " +
								"values(?,?,?,?,?,?,now())" ; 
	
	public static final String SQL_WEBSORTINFO_SELECT = "select id from web_sort_info where domainname=? and sorttype=?" ; 
	
	public static final String SQL_WEBSORTINFO_SELECTAll = "select * from web_sort_info limit ?,?" ;
	
	public static final String SQL_WEBSORTINFO_UPDATE = "update web_sort_info set title=?,domainname=?,sort=?,"+
								"description=?,sorttype=?,createtime=now() where id=?";
}
