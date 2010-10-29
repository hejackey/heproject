package com.jackey.lucene.index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;

import com.jackey.lucene.dao.AlexaDao;
import com.jackey.parser.bean.AlexBean;
import com.jackey.parser.dao.DbConnection;

public class AlexaIndexServer extends BaseIndexServer {
	private Logger log = Logger.getLogger(AlexaIndexServer.class);
	
	@Override
	public void createIndex(IndexWriter writer) {
		try{
			AlexaDao dao = new AlexaDao(DbConnection.getDBConnection());			
			List<AlexBean> aList = dao.getAlexas(2,10);
			
			for(AlexBean alex : aList){
				Document doc = new Document();
				doc.add(new Field("title",alex.getTitle(),Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("domain",alex.getDomain(),Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("desc",alex.getDesc(),Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("sorttype",String.valueOf(alex.getSorttype()),Field.Store.YES, Field.Index.ANALYZED));
				
				writer.setUseCompoundFile(false);
				writer.addDocument(doc);	
				writer.optimize();
			}
						
			writer.close();
		}catch(CorruptIndexException e){;
			log.error("create createIndex CorruptIndexException:"+e.getMessage());
		}
		catch(IOException ex){;
			log.error("create createIndex IOException:"+ex.getMessage());
		}
		catch (SQLException e) {
			log.error("create createIndex SQLException:"+e.getMessage());
			e.printStackTrace();
		}
	}

	public List searchIndex(String column,String key){		
		try {
			IndexSearcher isearcher = getIndexSearch();
			QueryParser parser = getQueryParser(column);			
			Query query = parser.parse(key);
			
			ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
			List alexList = new ArrayList();
			
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println("title="+hitDoc.get("title")+","+
						"domain="+hitDoc.get("domain")+","+
						"desc="+hitDoc.get("desc")+","+
						"sorttype="+hitDoc.get("sorttype"));
				
				AlexBean bean = new AlexBean();
				bean.setTitle(hitDoc.get("title"));
				bean.setDomain(hitDoc.get("domain"));
				bean.setDesc(hitDoc.get("desc"));
				bean.setSorttype(Integer.valueOf(hitDoc.get("sorttype")));
				
				alexList.add(bean);
			}
			isearcher.close();

			return alexList;			
		} catch (ParseException e) {
			log.error("searchIndex ParseException:"+e.getMessage());
			e.printStackTrace();

			return null;
		}
		 catch (IOException e) {
			log.error("searchIndex IOException:"+e.getMessage());
			e.printStackTrace();

			return null;
		}
		
	}	
}
