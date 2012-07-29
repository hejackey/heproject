package com.jackey.gc.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class GenCode {
	private static final Logger log = Logger.getLogger(GenCode.class);
	private static final String MAVEN_SRC_PATH="\\src\\main\\java\\";
	private static final String PROPERTIES_NAME="gen_code.properties";
	private static final String GC_IP=PropertiesUtil.getValueFromPropertyByKey(PROPERTIES_NAME, "GC_IP");
	private static final String GC_PORT=PropertiesUtil.getValueFromPropertyByKey(PROPERTIES_NAME, "GC_PORT");
	private static final String GC_DB_NAME=PropertiesUtil.getValueFromPropertyByKey(PROPERTIES_NAME, "GC_DB_NAME");
	private static final String GC_USER=PropertiesUtil.getValueFromPropertyByKey(PROPERTIES_NAME, "GC_USER");
	private static final String GC_PASS=PropertiesUtil.getValueFromPropertyByKey(PROPERTIES_NAME, "GC_PASS");
	private static final String GC_TABLE_NAME=PropertiesUtil.getValueFromPropertyByKey(PROPERTIES_NAME, "GC_TABLE_NAME");
	private static final String GC_PACK_NAME=PropertiesUtil.getValueFromPropertyByKey(PROPERTIES_NAME, "GC_PACK_NAME");
	private static final String GC_PRI_KEY=PropertiesUtil.getValueFromPropertyByKey(PROPERTIES_NAME, "GC_PRI_KEY");
	private static final String ENTER_SPACES="\r\n";
	private static Connection con;
	
	/**
	 * 初始化MySql数据库信息
	 * @param ip	数据库所在机器ip地址
	 * @param port	数据库端口
	 * @param dbName	数据库名称
	 * @param user	数据库用户名
	 * @param pass	数据库密码
	 */
	private void initDb(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = new StringBuffer("jdbc:mysql://").append(GC_IP)
					.append(":").append(GC_PORT).append("/").append(GC_DB_NAME).toString();
			
			con = DriverManager.getConnection(url,GC_USER,GC_PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取表的metadata信息
	 * @param tableName	表名
	 * @return
	 * @throws SQLException
	 */
	private ResultSetMetaData getTableMetaData() throws SQLException{
		if(con == null){
			log.error("please fisrt initDb");
			return null;
		}
		
		Statement stm = con.createStatement();
		String sql = "select * from "+GC_TABLE_NAME+" limit 0,1";
		
		log.info(new StringBuffer("getTableMetaDate sql is ").append(sql).toString());
		
		ResultSet rs = stm.executeQuery(sql);

		return rs.getMetaData();
	}

	private static GenCode gc = new GenCode();
	public static void genCode() throws SQLException, IOException{
		gc.genCodeModel();
		gc.genCodeService();
	}
	
	/**
	 * 生成model文件
	 * @throws SQLException
	 * @throws IOException
	 */
	private void genCodeModel() throws SQLException, IOException{
		gc.initDb();
		
		ResultSetMetaData rsmd = gc.getTableMetaData();
		int columnCount = rsmd.getColumnCount();
		
		StringBuffer sb = new StringBuffer();
		sb.append("package ").append(GC_PACK_NAME).append(".")
			.append("model;").append(ENTER_SPACES).append(ENTER_SPACES);
		sb.append("import java.io.Serializable;").append(ENTER_SPACES);
		sb.append("import javax.persistence.Column;").append(ENTER_SPACES);
		sb.append("import javax.persistence.Entity;").append(ENTER_SPACES);
		sb.append("import javax.persistence.GeneratedValue;").append(ENTER_SPACES);
		sb.append("import javax.persistence.GenerationType;").append(ENTER_SPACES);
		sb.append("import javax.persistence.Id;").append(ENTER_SPACES);
		sb.append("import javax.persistence.Table;").append(ENTER_SPACES);
		
		List<String> ccNameList = new ArrayList<String>();
		List<String> cNameList = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();

		String fieldInfo = gc.gcFieldInfo(columnCount,rsmd,map,cNameList,ccNameList);
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()){
			String pk = it.next();
			sb.append("import ").append(map.get(pk)).append(";").append(ENTER_SPACES);
		}
		
		sb.append(ENTER_SPACES).append("@Entity");
		sb.append(ENTER_SPACES).append("@Table(name=\"").append(GC_TABLE_NAME).append("\")")
			.append(ENTER_SPACES);
		sb.append("public class ").append(gc.getClassName(GC_TABLE_NAME))
			.append(" implements Serializable {").append(ENTER_SPACES);
		
		sb.append(fieldInfo);
		
		sb.append(ENTER_SPACES);
		sb.append(gc.gcGetSetMethod(rsmd,cNameList,ccNameList));
		
		sb.append("}");
		

		String projectPath = System.getProperty("user.dir");
		String pkName = (GC_PACK_NAME+".model").replace(".", "\\");
		String srcPath = projectPath+MAVEN_SRC_PATH+pkName;
		
		File file = new File(srcPath);
		if(!file.exists()){
			file.mkdirs();
		}
		
		File javaFile = new File(srcPath+"\\"+gc.getClassName(GC_TABLE_NAME)+".java");
		FileOutputStream out = new FileOutputStream(javaFile);  
		BufferedOutputStream bos = new BufferedOutputStream(out);
		bos.write(sb.toString().getBytes());
		
		bos.close();
	}
	
	private void genCodeService() throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append("package ").append(GC_PACK_NAME).append(".")
			.append("service;").append(ENTER_SPACES).append(ENTER_SPACES);
		sb.append("import org.osoa.sca.annotations.Remotable").append(ENTER_SPACES);
		sb.append("import com.sohu.blog.exception.ServiceDaoException;").append(ENTER_SPACES);
		sb.append("import com.sohu.blog.exception.ServiceException;").append(ENTER_SPACES);
		
		sb.append("import ").append(GC_PACK_NAME).append(".model.")
			.append(gc.getClassName(GC_TABLE_NAME)).append(";")
			.append(ENTER_SPACES).append(ENTER_SPACES);
		
		sb.append("@Remotable").append(ENTER_SPACES);
		
		sb.append("public interface ").append(gc.getClassName(GC_TABLE_NAME))
			.append("Service {").append(ENTER_SPACES);
		
		sb.append("    public ").append(gc.getClassName(GC_TABLE_NAME)).append(" ")
			.append("get").append(gc.getClassName(GC_TABLE_NAME))
			.append("(Long id)throws ServiceDaoException, ServiceException;")
			.append(ENTER_SPACES).append(ENTER_SPACES);
		
		sb.append("    public ").append(gc.getClassName(GC_TABLE_NAME)).append(" ")
			.append("save").append(gc.getClassName(GC_TABLE_NAME))
			.append("(").append(gc.getClassName(GC_TABLE_NAME))
			.append(" model)throws ServiceDaoException, ServiceException;")
			.append(ENTER_SPACES).append(ENTER_SPACES);
		
		sb.append("    public ").append("boolean ")
			.append("update").append(gc.getClassName(GC_TABLE_NAME))
			.append("(").append(gc.getClassName(GC_TABLE_NAME))
			.append(" model)throws ServiceDaoException, ServiceException;")
			.append(ENTER_SPACES).append(ENTER_SPACES);
		
		sb.append("}");
		
		String projectPath = System.getProperty("user.dir");
		String pkName = (GC_PACK_NAME+".service").replace(".", "\\");
		String srcPath = projectPath+MAVEN_SRC_PATH+pkName;
		
		File file = new File(srcPath);
		if(!file.exists()){
			file.mkdirs();
		}
		
		File javaFile = new File(srcPath+"\\"+gc.getClassName(GC_TABLE_NAME)+"Service.java");
		FileOutputStream out = new FileOutputStream(javaFile);  
		BufferedOutputStream bos = new BufferedOutputStream(out);
		bos.write(sb.toString().getBytes());
		
		bos.close();
	}
	/**
	 * 类或field名每个词第一个字母大写
	 * @param tableName
	 * @return
	 */
	private String getClassName(String tableName) {
		
		if(tableName == null || tableName =="") {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		String[] tNames = tableName.split("_");
		
		for(String tname : tNames) {
			sb.append(tname.replaceFirst(String.valueOf(tname.charAt(0)), 
					String.valueOf(tname.charAt(0)).toUpperCase()));
		}
		
		return sb.toString();
	}
	
	/**
	 * 除第一个词首字母不大写，其他词字母都大写
	 * @param fieldName
	 * @return
	 */
	private String getFieldName(String fieldName) {
		
		if(fieldName == null || fieldName =="") {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		String[] fNames = fieldName.split("_");
		
		for(int i=0;i<fNames.length;i++) {
			if(i==0)
				sb.append(fNames[i]);
			else
				sb.append(fNames[i].replaceFirst(String.valueOf(fNames[i].charAt(0)), 
						String.valueOf(fNames[i].charAt(0)).toUpperCase()));
		}
		
		return sb.toString();
	}
	
	/**
	 * 生成类的get、set方法
	 * @param cNameList
	 * @param ccNameList
	 * @return
	 * @throws SQLException 
	 */
	private String gcGetSetMethod(ResultSetMetaData rsmd,List<String> cNameList,List<String> ccNameList) throws SQLException{
		StringBuffer sb = new StringBuffer();
		for(int j=0; j<cNameList.size(); j++) {
			if(cNameList.get(j).equals(GC_PRI_KEY)){
				sb.append("    @Id").append(ENTER_SPACES)
					.append("    @GeneratedValue(strategy = GenerationType.AUTO)").append(ENTER_SPACES)
					.append("    @Column(name=\"").append(rsmd.getColumnName(j+1)).append("\")")
					.append(ENTER_SPACES);
			} else {
				sb.append("    @Column(name=\"").append(rsmd.getColumnName(j+1)).append("\")")
					.append(ENTER_SPACES);
			}
			
			sb.append("    public ").append(ccNameList.get(j))
				.append(" get").append(getClassName(cNameList.get(j)))
				.append("() {").append(ENTER_SPACES)
				.append("        return ").append(cNameList.get(j)).append(";")
				.append(ENTER_SPACES).append("    }").append(ENTER_SPACES).append(ENTER_SPACES);
			
			sb.append("    public void set").append(getClassName(cNameList.get(j)))
				.append("(").append(ccNameList.get(j)).append(" ")
				.append(cNameList.get(j)).append(") {").append(ENTER_SPACES)
				.append("        this.").append(cNameList.get(j))
				.append(" = ").append(cNameList.get(j)).append(";")
				.append(ENTER_SPACES).append("    }").append(ENTER_SPACES).append(ENTER_SPACES);
		}
		
		return sb.toString();
	}
	
	/**
	 * 生成field信息
	 * @param columnCount
	 * @param rsmd
	 * @param map
	 * @param cNameList
	 * @param ccNameList
	 * @return
	 * @throws SQLException
	 */
	private String gcFieldInfo(int columnCount,ResultSetMetaData rsmd,
			Map<String,String> map,List<String> cNameList,List<String> ccNameList) throws SQLException{
		StringBuffer sb = new StringBuffer();
		
		for(int i=1;i<=columnCount;i++){
			
			String ccName = rsmd.getColumnClassName(i);
			if(!map.containsKey(ccName));
				map.put(ccName, ccName);
				
			String cName = getFieldName(rsmd.getColumnName(i));
			
			sb.append("    ").append("private ")
				.append(ccName.substring(ccName.lastIndexOf(".")+1))
				.append(" ").append(cName).append(";")
				.append(ENTER_SPACES);
			
			cNameList.add(cName);
			ccNameList.add(ccName.substring(ccName.lastIndexOf(".")+1));
		}
		
		return sb.toString();
	}
}
