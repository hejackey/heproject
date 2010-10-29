package com.jackey.lucene.index;


import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.jackey.lucene.util.ConfigUtil;

/**
 * 索引服务抽象接口
 * @author Administrator
 *
 */
public abstract class BaseIndexServer {
	private Logger log = Logger.getLogger(BaseIndexServer.class);
	
	
	public void createIndex(IndexWriter writer){};
	
	public IndexWriter getIndexWriter(){
		Analyzer analyzer = new IKAnalyzer();// 采用的分词器
		
		try {
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
}
