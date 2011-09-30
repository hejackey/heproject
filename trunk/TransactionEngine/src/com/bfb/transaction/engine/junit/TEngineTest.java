package com.bfb.transaction.engine.junit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.bfb.transaction.engine.parserfile.ParserXmlFile;

public class TEngineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("please input transaction cmd:");

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			String cmd = reader.readLine();
			
			URL url = ClassLoader.getSystemResource(cmd+".xml");
			String filePath = url.getFile();
			
			long time1 = System.currentTimeMillis();
			
			ParserXmlFile parserFile = new ParserXmlFile();
			Map<Object,Object> para = new HashMap<Object,Object>();
			para.put("userid", "123");
			
			parserFile.parserFile(filePath,para);
			
			long time2 = System.currentTimeMillis();
			
			System.out.println(time2-time1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
