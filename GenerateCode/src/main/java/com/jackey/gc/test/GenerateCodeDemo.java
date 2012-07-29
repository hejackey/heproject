package com.jackey.gc.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
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

public class GenerateCodeDemo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8126679126868064229L;
	private static final String ENTER_SPACES="\r\n";
	private static Connection con;
	
	public static void initDb(String ip,int port,String dbName,
			String user,String pass){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = new StringBuffer("jdbc:mysql://").append(ip)
					.append(":").append(port).append("/").append(dbName).toString();
			
			con = DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private static final String MAVEN_SRC_PATH="\\src\\main\\java\\";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("package com.jackey.gc.model;").append(ENTER_SPACES).append(ENTER_SPACES);
			sb.append("import java.io.Serializable;").append(ENTER_SPACES);
			sb.append("import javax.persistence.Column;").append(ENTER_SPACES);
			sb.append("import javax.persistence.Entity;").append(ENTER_SPACES);
			sb.append("import javax.persistence.GeneratedValue;").append(ENTER_SPACES);
			sb.append("import javax.persistence.GenerationType;").append(ENTER_SPACES);
			sb.append("import javax.persistence.Id;").append(ENTER_SPACES);
			sb.append("import javax.persistence.Table;").append(ENTER_SPACES);
			
			initDb("10.10.77.43",3306,"spaces_activity","umsManager","daYfs5F");

			Statement stm = con.createStatement();
			String tableName = "recom_video";
			String sql = "select * from "+tableName+" where id=100";
			
			ResultSet rs = stm.executeQuery(sql);
			if (rs != null) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				
				List<String> ccNameList = new ArrayList<String>();
				List<String> cNameList = new ArrayList<String>();
				Map<String,String> map = new HashMap<String,String>();
				
				String fieldInfo = gcFieldInfo(columnCount,rsmd,map,cNameList,ccNameList);
				Set<String> set = map.keySet();
				Iterator<String> it = set.iterator();
				while(it.hasNext()){
					String pk = it.next();
					sb.append("import ").append(map.get(pk)).append(";").append(ENTER_SPACES);
				}
				
				sb.append(ENTER_SPACES).append("@Entity");
				sb.append(ENTER_SPACES).append("@Table(name=\"").append(tableName).append("\")")
					.append(ENTER_SPACES);
				sb.append("public class ").append(getClassName("recom_video"))
					.append(" implements Serializable {").append(ENTER_SPACES);
				
				sb.append(fieldInfo);
				
				sb.append(ENTER_SPACES);
				sb.append(gcGetSetMethod(rsmd,cNameList,ccNameList));
			}
			
			sb.append("}");
			

			String projectPath = System.getProperty("user.dir");
			String pkName = "com.jackey.gc.user.model".replace(".", "\\");
			String srcPath = projectPath+MAVEN_SRC_PATH+pkName;
			
			File file = new File(srcPath);
			if(!file.exists()){
				file.mkdirs();
			}
			
			File javaFile = new File(srcPath+"\\"+getClassName("recom_video")+".java");
			FileOutputStream out = new FileOutputStream(javaFile);  
			BufferedOutputStream bos = new BufferedOutputStream(out);
			bos.write(sb.toString().getBytes());
			
			bos.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 类或field名每个词第一个字母大写
	 * @param tableName
	 * @return
	 */
	public static String getClassName(String tableName) {
		
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
	public static String getFieldName(String fieldName) {
		
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
	public static String gcGetSetMethod(ResultSetMetaData rsmd,List<String> cNameList,List<String> ccNameList) throws SQLException{
		StringBuffer sb = new StringBuffer();
		for(int j=0; j<cNameList.size(); j++) {
			if(cNameList.get(j).equals("id")){
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
	public static String gcFieldInfo(int columnCount,ResultSetMetaData rsmd,
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
