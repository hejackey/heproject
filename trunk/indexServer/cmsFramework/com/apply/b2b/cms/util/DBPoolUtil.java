package com.apply.b2b.cms.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.apply.b2b.cms.config.ConfigBuilderFactory;
import com.moobao.crypt.util.CryptUtil;

/**
 * 
 * @author luoweifeng
 * 
 */
public class DBPoolUtil {
	private static final String DB_PROPERTY = "dbpoolconfig.properties";
	private static DataSource dataSource;
	private static DBPoolUtil dbPoolUtil;

	private DBPoolUtil(int encrypted) {
		Properties paraProps = new Properties();
		
		paraProps = ConfigBuilderFactory.getConfigBuilder(DB_PROPERTY)
				.getAllProperties();
        
		String enScyptStr = paraProps.getProperty("password");
		String deScyptStr = CryptUtil.descypt(enScyptStr);
		paraProps.remove("password");
		paraProps.setProperty("password", deScyptStr);
		
		try {
			dataSource = BasicDataSourceFactory.createDataSource(paraProps);
		} catch (Exception e) {
		}
	}
	
	
	
	private DBPoolUtil(int encrypted,int tested) {
		Properties paraProps = new Properties();
		
		paraProps = ConfigBuilderFactory.getConfigBuilder(DB_PROPERTY)
				.getAllProperties();
        
		String enScyptStr = paraProps.getProperty("password");
		String deScyptStr = CryptUtil.descypt(enScyptStr);
		paraProps.remove("password");
		paraProps.setProperty("password", deScyptStr);
		
		System.out.println("url:" + paraProps.getProperty("url"));
		System.out.println("username:" + paraProps.getProperty("username"));
		System.out.println("password:" + paraProps.getProperty("password"));
		System.out.println("");
		System.out.println("");
		
		
		
		try {
			dataSource = BasicDataSourceFactory.createDataSource(paraProps);
		} catch (Exception e) {
		}
	}
	
//	driverClassName=oracle.jdbc.driver.OracleDriver
//	url=jdbc:oracle:thin:@192.168.1.248:1521:ora9
//	#url=jdbc:oracle:thin:@192.168.1.252:1521:moobao
//	username=moobao
//	password=moobao
//	initialSize=3
//	maxActive=10
//	maxIdle=3
//	maxWait=6000
//	validationQuery=SELECT 1 FROM DUAL
//	removeAbandoned=true
//	removeAbandonedTimeout=60
//	logAbandoned=true
	
	private DBPoolUtil(String dbName, String dbip, String dbUserName,String dbPwd) {
		Properties paraProps = new Properties();
        paraProps.setProperty("driverClassName", "oracle.jdbc.driver.OracleDriver");
        paraProps.setProperty("url", "jdbc:oracle:thin:@" + dbip + ":1521:" + dbName);
        
        paraProps.setProperty("username", dbUserName);
        paraProps.setProperty("password", dbPwd);
        paraProps.setProperty("initialSize", "3");
        paraProps.setProperty("maxActive", "6");
        paraProps.setProperty("maxIdle", "3");
        paraProps.setProperty("maxWait", "6000");
        
        paraProps.setProperty("validationQuery", "SELECT 1 FROM DUAL");
        paraProps.setProperty("removeAbandoned", "true");
        paraProps.setProperty("removeAbandonedTimeout", "60");
        paraProps.setProperty("logAbandoned", "true");
        
        try {
			dataSource = BasicDataSourceFactory.createDataSource(paraProps);
		} catch (Exception e) {
		}
	}
    
	private DBPoolUtil(String jndiDataSourceName) {
		Context ctx = null;
		try {
			ctx = new InitialContext();
			try {
				dataSource = (DataSource)ctx.lookup(jndiDataSourceName);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				ctx.close();
			} catch (Exception e) {
			}
		}
	}
	
	public DBPoolUtil(String jndiName, String type) {
		Context ctx = null;
		Hashtable ht = new Hashtable();
		ht.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		ht.put(Context.PROVIDER_URL, "iiop://192.168.1.250:7001");
      ht.put(Context.SECURITY_PRINCIPAL, "moobao");
      ht.put(Context.SECURITY_CREDENTIALS, "12345678");
        
		try {
			ctx = new InitialContext(ht); // Use the context in your program
			try {
				dataSource = (DataSource)ctx.lookup(jndiName);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		} catch (NamingException e) { // a failure occurred
			e.printStackTrace();
		} finally {
			try {
				ctx.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    
	public DBPoolUtil() {
		Properties paraProps = new Properties();

		paraProps = ConfigBuilderFactory.getConfigBuilder(DB_PROPERTY)
				.getAllProperties();

		try {
			dataSource = BasicDataSourceFactory.createDataSource(paraProps);
		} catch (Exception e) {
		}
	}
	
	public static synchronized DBPoolUtil getInstance() {
		if (dbPoolUtil == null) {
			dbPoolUtil = new DBPoolUtil();
		}
		return dbPoolUtil;
	}

	public static synchronized DBPoolUtil getRomoteJndiInstance(String jndiName) {
		if (dbPoolUtil == null) {
			dbPoolUtil = new DBPoolUtil(jndiName,"remote");
		}
		return dbPoolUtil;
	}
	
	
	public static synchronized DBPoolUtil getEnCryptInstance() {
		if (dbPoolUtil == null) {
			dbPoolUtil = new DBPoolUtil(1);
		}
		return dbPoolUtil;
	}
	
	
	private static synchronized DBPoolUtil getEnCryptInstancetest() {
		if (dbPoolUtil == null) {
			
			dbPoolUtil = new DBPoolUtil(1,1);
		}
		return dbPoolUtil;
	}
	
	public static synchronized DBPoolUtil getInstance(String dbName, String dbip, String dbUserName,String dbPwd) {
		if (dbPoolUtil == null) {
			dbPoolUtil = new DBPoolUtil( dbName,  dbip,  dbUserName, dbPwd);
		}
		return dbPoolUtil;
	}
	
	public static synchronized DBPoolUtil getInstance(String jndiName) {
		if (dbPoolUtil == null) {
			dbPoolUtil = new DBPoolUtil(jndiName);
		}
		return dbPoolUtil;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] arg) {
		Connection conn = DBPoolUtil.getEnCryptInstancetest().getConnection();
		if(conn != null){
		try {
			Statement dd = conn.createStatement();

			ResultSet sd = dd.executeQuery("select 1 from dual");

			while (sd.next()) {
				System.out.println(sd.getInt(1));
			}
			System.out.println("数据库连接成功!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		}else{
			System.out.println("数据库连接失败!");
		}
	}
}