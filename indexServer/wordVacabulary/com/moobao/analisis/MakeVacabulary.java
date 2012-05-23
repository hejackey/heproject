
/**
 * 获取手机"品种"、"型号"与"高级搜索"属性,将其存入词库
 * @author liuxueyong
 */
package com.moobao.analisis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.moobao.config.PropertyConfiguration;

public class MakeVacabulary {

	//手机品种
	private static Set setType = new HashSet();
	//手机型号�
	private static Set setModel = new HashSet();
	private static String sql = "";
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void makeVacabulary() {
		// TODO Auto-generated method stub
		GetFieldFromDb gf = new GetFieldFromDb(); 
		sql = "select valuename from dict_basetype_value where typeid=102";
		setType = gf.getField(sql, "valuename");
		
		sql = "select productmodel from product_list";
		setModel = gf.getField(sql, "productmodel");
		
		//设置词库文件product.dic的路径 
		WriteVacabulary builder = new WriteVacabulary();
		builder.setWordvacabularyPath( PropertyConfiguration.getWordDictionary() );
		try {
			builder.writeToFile(setType);
			builder.writeToFile(setModel);
		}
		catch( IOException ex ) {
			ex.printStackTrace();
		}
		//将"高级搜索"属性写入词库
		GetHighProperty.writeHighProperty();
	}
	
	public static void main( String[] args ) {
		MakeVacabulary.makeVacabulary();
	}
}