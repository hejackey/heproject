package com.jackey.lucene.index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;

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
			log.error("create AlexaIndexServer CorruptIndexException:"+e.getMessage());
		}
		catch(IOException ex){;
			log.error("create AlexaIndexServer IOException:"+ex.getMessage());
		}
		catch (SQLException e) {
			log.error("create AlexaIndexServer SQLException:"+e.getMessage());
			e.printStackTrace();
		}
	}

}
