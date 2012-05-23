package com.liu.index;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;

import com.moobao.analisis.AnalysisFactory;

public class Search {
	//The store directory of index
	private static String INDEX_STORE_PATH = "E:\\index1";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * @param null
		 * @Exception IOException
		 * @return null
		 */
			
			try {
				//create a IndexSearcher Object
				IndexSearcher searcher = new IndexSearcher( INDEX_STORE_PATH ) ;
				
				//create two Term Object.
				//Term t1 = new Term( "name", "E578" );
				//Term t2 = new Term( "author", "hatcher" );
				
				//create two TermQuery Object.
				//Query query = new TermQuery( t1 );
				//MMAnalyzer analyzer = new MMAnalyzer();
				QueryParser query = new QueryParser("name:手机 通话", AnalysisFactory.getIndexingAnalisis() );
				
				//TermQuery q2 = new TermQuery( t2 );
				//create PhraseQuery Object
				//PhraseQuery query = new PhraseQuery();
				//add the TermQueries to PhraseQuery.
				//query.add( t1 );
				//query.add( t2 );
				//search
				Hits hits = null;
				try {
					hits = searcher.search( query.parse("name:手机 通话") );
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for( int j = 0; j < hits.length(); j ++ ) {
					Document doc = hits.doc( j );
					System.out.println( "品种:" + doc.get( "name" ) );
					System.out.println( "ID:" + doc.get( "productid" ) );
//					System.out.println( "����:" + doc.get( "author" ) );
//					System.out.println( "�����:" + doc.get( "publisher" ) );
//					System.out.println( "�������:" + doc.get( "pubDate" ) );
//					System.out.println( "�۸�:" + doc.get( "price" ) );
//					System.out.println( "����:" + doc.get( "desc" ) );
//					System.out.println( "------------------------------------" );
				}
			}
			catch( IOException e ) {
				e.printStackTrace();
			}
		}
}
