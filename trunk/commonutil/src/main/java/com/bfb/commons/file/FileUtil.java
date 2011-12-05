package com.bfb.commons.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

public class FileUtil {
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
}
