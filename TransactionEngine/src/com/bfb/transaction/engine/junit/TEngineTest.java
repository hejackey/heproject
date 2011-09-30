package com.bfb.transaction.engine.junit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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
			
			ParserXmlFile parserFile = new ParserXmlFile();
			parserFile.parserFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
