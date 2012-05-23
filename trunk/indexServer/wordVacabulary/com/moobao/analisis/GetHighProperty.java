
/**
 * 得到高级搜索的各个属性,存入一个StringBuffer,然后写入product.dic中
 * @author liuxueyong
 */
package com.moobao.analisis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.moobao.config.PropertyConfiguration;

public class GetHighProperty {
	//从配置文件中得到高级搜索属性与词库的路径
	private static String propertyPath = PropertyConfiguration.getHighPropertyPath();
	private static String vacabularyPath = PropertyConfiguration.getWordDictionary();
	//定义两个File对象
	private static File file = null;
	private static File file_write = null;
	//定义一个BufferedReader对象
	private static BufferedReader br = null;
	//定义一个FileWriter对象
	private static FileWriter fw = null;
	//定义一个StringBuffer对象
	private static StringBuffer sb = null;
	
	/**
	 * @param null
	 * @exception IOException
	 * @return null
	 */
	public static void writeHighProperty() {
		
		String line = "";
		try {
			//读取属性
			file = new File( propertyPath );
			file_write = new File( vacabularyPath );
			br = new BufferedReader( new FileReader( file ) );
			sb = new StringBuffer();
			line = br.readLine();
			while( line != null ) {
				sb.append(line+"\r\n");
				line = br.readLine();
			}
			//写入词库文件中
			fw = new FileWriter( file_write, true );
			fw.append( sb.toString() );
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
				fw.close();
			}
			catch( IOException ex ) {
				ex.printStackTrace();
			}
		}
	}
}
