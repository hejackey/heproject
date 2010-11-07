package com.jackey.lucene.index;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.jackey.lucene.util.ConfigUtil;

/**
 * �����������ӿ�
 * @author Administrator
 *
 */
public abstract class BaseIndexServer {
	private Logger log = Logger.getLogger(BaseIndexServer.class);
	
	/**
	 * ��������
	 * @param writer
	 */
	public void createIndex(IndexWriter writer){
		
	}
	
	/**
	 * ��������
	 * @param writer
	 */
	public List searchIndex(String column,String key){
		return null;
	}	
	
	/**
	 * ��ȡindexwriter������
	 * @return
	 */
	public IndexWriter getIndexWriter(){
		Analyzer analyzer = new IKAnalyzer();// ���õķִ���
		
		try {//��Ҫ�޸ĳɵ���ģʽ
			return new IndexWriter(FSDirectory.open(new File(
					ConfigUtil.getConfProperty(ConfigUtil.confFile, ConfigUtil.INDEX_FILE_PATH))), analyzer, true,
					IndexWriter.MaxFieldLength.LIMITED);
		} catch (CorruptIndexException e) {
			log.error("getIndexWriter CorruptIndexException:"+e.getMessage());
			e.printStackTrace();
			return null;
		} catch (LockObtainFailedException e) {
			log.error("getIndexWriter LockObtainFailedException:"+e.getMessage());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			log.error("getIndexWriter IOException:"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��ȡһ��������
	 * @return
	 */
	public IndexSearcher getIndexSearch() {
		
		try {
			Directory directory = FSDirectory.open(new File(ConfigUtil.getConfProperty(ConfigUtil.confFile, 
					ConfigUtil.INDEX_FILE_PATH)));			

			return new IndexSearcher(directory, true); 
		} catch (IOException e) {
			log.error("getIndexSearch IOException:"+e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * ��ȡһ��queryparser
	 * @param column Ҫ��ѯ���ֶ�
	 * @return
	 */
	public QueryParser getQueryParser(String column){
		Analyzer analyzer = new IKAnalyzer();
		
		return new QueryParser(Version.LUCENE_30, column, analyzer);
	}	
}
