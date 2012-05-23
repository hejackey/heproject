
/**
 * 获取分词器.
 */
package com.moobao.analisis;

import java.io.FileReader;
import java.io.IOException;
import jeasy.analysis.MMAnalyzer;
import com.moobao.config.PropertyConfiguration;

public class AnalysisFactory {

	private static MMAnalyzer analyzer_search = null;
	private static MMAnalyzer analyzer_index = null;
	//词库文件的path
	private static String dictionary_file = PropertyConfiguration.getWordDictionary();;
	//定义一个文件读取对象
	private static FileReader reader = null;
	
	/**
	 * 得到一个JEAnalyzer对象
	 * @param null
	 * @throws IOException
	 * @return MMAnalyzer
	 * @author liuxueyong
	 */
	public static synchronized MMAnalyzer getSearchingAnalisis() {
	
		if( analyzer_search == null ) {
			
			analyzer_search = new MMAnalyzer();
		    try {
			    reader = new FileReader(dictionary_file);
		    }
		    catch( IOException ex ) {
			    ex.printStackTrace();
			    System.err.println("读取词库文件出错!");
		    }
		
		    analyzer_search.addDictionary( reader );
		}
		
		return analyzer_search;
	}
		
	/**
	 * 得到一个JEAnalyzer对象
	 * @param null
	 * @throws IOException
	 * @return MMAnalyzer
	 * @author liuxueyong
	 */
	public static synchronized MMAnalyzer getIndexingAnalisis() {
		
		if( analyzer_index == null ) {
			analyzer_index = new MMAnalyzer();
			try {
				reader = new FileReader(dictionary_file);
			}
			catch( IOException ex ) {
				ex.printStackTrace();
				System.err.println("读取词库文件出错!");
			}
			analyzer_index.addDictionary( reader );
		}
		
		return analyzer_index;
	}
}
