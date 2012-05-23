
/**
 * �将"品种"、"型号"写入词库
 * @author liuxueyong
 */
package com.moobao.analisis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class WriteVacabulary {

	//词库文件的路径
	private String wordvacabularypath = null;
	//定义一个BufferedWriter对象.
	private BufferedWriter writer = null;

	/**
	 * 写词库文件
	 * @param set
	 * @throws IOException
	 * @return null
	 */
	public void writeToFile( Set set ) throws IOException {

		try {
			if (wordvacabularypath == null) {
				throw new IOException("�ʿ��ŵ��ļ�û没有指定词库文件的路径!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			writer = new BufferedWriter(new FileWriter(wordvacabularypath));
			Iterator it = set.iterator();
			while( it.hasNext() ) {
				writer.write((String) it.next());
				writer.newLine();
			}
		}
		catch( IOException ex ) {
			ex.printStackTrace();
			System.err.println("建立、打开或写词库文件出错!");
		}
		finally {
			writer.close();
		}
	}
	/**
	 * 指定词库文件的路径
	 * @param wordvacabularypath
	 * @exception no thrown Exception
	 * @return null
	 */
	
	public void setWordvacabularyPath(String wordvacabularypath) {
		this.wordvacabularypath = wordvacabularypath;
	}
}
