package com.jackey.lucene.index;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.jackey.parser.bean.AlexBean;
import com.jackey.parser.dao.DbConnection;
import com.jackey.solr.dao.AlexaSolrDao;

public class AlexaLucene {
	public static void createAlexaIndex() throws CorruptIndexException, LockObtainFailedException, IOException{
		try {
			AlexaSolrDao dao = new AlexaSolrDao(DbConnection.getDBConnection());
			AlexBean bean = new AlexBean(2,10);
			List<AlexBean> alexList = dao.getAlexas(bean);
			
			 Analyzer analyzer = new IKAnalyzer();// 采用的分词器

			// 第三个参数 为true表示新建，false表示添加到原有索引中
			IndexWriter writer = new IndexWriter(FSDirectory.open(new File(
					"d:\\lucene\\index")), analyzer, false,
					IndexWriter.MaxFieldLength.LIMITED);
			/*IndexWriter writer = new IndexWriter("d:\\lucene\\index", analyzer, true);*/
			for(AlexBean alex : alexList){
				Document doc = new Document();
				doc.add(new Field("title",alex.getTitle(),Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("domain",alex.getDomain(),Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("desc",alex.getDesc(),Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("sorttype",String.valueOf(alex.getSorttype()),Field.Store.YES, Field.Index.ANALYZED));
				
				writer.setUseCompoundFile(false);
				writer.addDocument(doc);
				writer.optimize();
				writer.close();
			}
			System.out.println(alexList.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try {
			AlexaLucene.createAlexaIndex();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
