package com.bfb.commons.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class FileUtil {
	private static Logger log = Logger.getLogger(FileUtil.class);
	
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
	
	public static void main(String[] args){
		File file = new File("d:\\test.log");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			PatternLayout pl = new PatternLayout();
			log.addAppender(new RollingFileAppender(pl,"test.log"));
			log.info("sfasd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
