package com.moobao.entertainment.indexTask;

import java.io.IOException;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.index.IndexWriter;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.config.PropertyConfiguration;

/**
 * 娱乐中心索引文件的优化.
 * @author liuxueyong
 */
public class EntertainmentOptimizeIndexTask {
	//获取娱乐中心索引存放的位置.
	private static final String indexPath = PropertyConfiguration.getEntertainmentIndexPath();
	//获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory.getIndexingAnalisis();
	
	/**
     * 娱乐中心索引文件的优化.
     * @param null
     * @exception IOException
     * @return null
     */
    public static void optimizeIndex() {
    	//初始化一个IndexWriter.
    	IndexWriter writer = null;
    	try {
    		writer = new IndexWriter( indexPath, analyser, false );
    		writer.optimize();
    		writer.close();
    	}
    	catch( IOException e ) {
    		e.printStackTrace();
    		System.err.println( "索引文件优化失败!" );
    	}
    	finally {
    		try {
        		writer.close();
        	}
        	catch( IOException e ) {
        		e.printStackTrace();;
        	}
    	}
    }
    
    public static void main( String[] args ) {
    	EntertainmentOptimizeIndexTask task = new EntertainmentOptimizeIndexTask();
    	task.optimizeIndex();
    }
}
