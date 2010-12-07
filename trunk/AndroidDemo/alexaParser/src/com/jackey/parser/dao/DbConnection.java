package com.jackey.parser.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jackey.parser.util.Constants;

/**
 * <p>Title: SDK For ZLWT Wap</p>
 * <p>Description: oracle获取数据库连接的接口实现�?/p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: zlwt</p>
 * @author 林华�?
 * @version 1.0
 */

public class DbConnection {
	
  public DbConnection() {
  }

  public static Connection getDBConnection() throws SQLException{
	  Constants.init();
	  Connection conn = null;
	  String connName = Constants.DB_CONNECTION_NAME;
	  String username = Constants.DB_USER_NAME;
	  String password = Constants.DB_PASSWORD;
	  String driver = Constants.DB_DRIVER;
	
	  try {
		  
		  Class.forName(driver);
		  conn = DriverManager.getConnection(connName, username, password);
	  }
	  catch (Exception e) {
		 e.printStackTrace();
	  }
	
	  return conn;
  }

  public static boolean Close(Connection curConn){
    try {
      if (curConn != null) {
        curConn.close();
        curConn = null;
      }
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

}
