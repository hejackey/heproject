
package com.apply.b2b.cms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

public class TemplateUtil {
    static Logger log4j=Logger.getLogger(TemplateUtil.class.getName());
	
	public static String readTemplate(String fileName){
		StringBuffer file = new StringBuffer();
		try {
			FileReader fr=new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
			String data = null;
			while( (data = br.readLine()) !=null ){
				file.append( data) ; 
				data = null;
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.toString();
	}
	
	public static void deleteFile(String fullName){
		if(fullName == null || fullName.length() == 0)
			return;
		File worker = new File(fullName);
		if (worker.exists()){
		  worker.delete();
		}
	}
}