package com.moobao.peijian.indexTask;

import java.io.IOException;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.index.IndexWriter;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.config.PropertyConfiguration;

/**
 * 配件索引文件的优化.
 * 
 * @author liuxueyong
 */
public class PeiJianOptimizeIndexTask {
	// 获取配件索引存放的位置.
	private static  String indexPath = PropertyConfiguration.getPeiJianIndexPath();
	// 获取到分词器.
	private static MMAnalyzer analyser = AnalysisFactory.getIndexingAnalisis();
	
	/**
	 * 手机索引文件的优化.
	 * 
	 * @param null
	 * @exception IOException
	 * @return null
	 */
	public static void optimizeIndex() {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(indexPath, analyser, false);
			writer.optimize();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("索引文件优化失败!");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
