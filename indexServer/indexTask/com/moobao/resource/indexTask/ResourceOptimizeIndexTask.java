package com.moobao.resource.indexTask;

import java.io.IOException;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.config.PropertyConfiguration;

/**
 * 资讯索引文件的优化.
 * @author liuxueyong
 */
public class ResourceOptimizeIndexTask {
	//获取资讯索引存放的位置.
	private static final String indexPath = PropertyConfiguration.getResourceIndexPath();
	//获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory.getIndexingAnalisis();
	
	/**
     * 资讯索引文件的优化.
     * @param null
     * @exception IOException
     * @return null
     */
    public synchronized static void optimizeIndex() {

    	try {
			//IndexReader reader = IndexReader.open( "E:\\index\\resourceIndex\\" );
			FSDirectory d = FSDirectory.getDirectory( indexPath, false );
	    	IndexReader.unlock(d); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}

    	IndexWriter writer = null;
    	try {
    		writer = new IndexWriter( indexPath, analyser, false );
    		writer.optimize();
    	}
    	catch( IOException e ) {
    		e.printStackTrace();
    		System.err.println( "索引文件优化失败!" );
    	}
    	finally {
    		try {
    			writer.close();
    		}
    		catch( IOException ex ) {
    			ex.printStackTrace();
    		}
    	}
    }
    
    public static void main( String[] args ) {
    	ResourceOptimizeIndexTask.optimizeIndex();
    }
}
