package com.bfb.commons.file;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.Random;

import org.apache.commons.lang.StringUtils;


public class BfbFileUtil {
	public static void writeFile(InputStream ins,String newfilename) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "gbk"));
		File outfile = new File(newfilename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
		String inLine = null;
		
		while ((inLine = reader.readLine()) != null) {
			if (!StringUtils.isEmpty(inLine)) {
				bw.flush();
				bw.write(inLine);
				bw.newLine();
			}
		}
	}
	
	/**
	 * 写文件，在文件结尾追加新文件内容
	 * @param fileName 文件名
	 * @param content	文件内容
	 * 一次写入过多报内存溢出
	 * 
	 */
	public static void writeFile(String fileName,String content){
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(fileName),true);
			fw.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long time1=System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<24999;i++){
			sb.append(i+"==============离开家ads富乐科技阿胜大立科技adsl分开就阿斯顿富乐科技斯蒂芬");
		}
		BfbFileUtil.writeFile("d:\\file.txt",sb.toString());
				
		long time2 = System.currentTimeMillis();
		System.out.println((time2-time1)/1000);
	}
	
}
