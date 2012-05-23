package com.liu.index;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import com.moobao.analisis.AnalysisFactory;

public class DocDel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			IndexWriter writer = new IndexWriter( "c:\\index\\", AnalysisFactory.getIndexingAnalisis(), true );
			Document doc1 = new Document();
			Document doc2 = new Document();
			Document doc3 = new Document();
			
			Field field = new Field( "ID", "1", Field.Store.YES, Field.Index.UN_TOKENIZED );
			Field field2 = new Field( "ID", "12", Field.Store.YES, Field.Index.UN_TOKENIZED );
			Field field3 = new Field( "ID", "1212", Field.Store.YES, Field.Index.UN_TOKENIZED );
			
			doc1.add(field);
			doc2.add(field2);
			doc3.add(field3);
			
			System.out.println( doc1 );
			System.out.println( doc2 );
			System.out.println( doc3 );
			
			writer.addDocument( doc1 );
			writer.addDocument( doc2 );
			writer.addDocument( doc3 );
			
			writer.close();
			
			System.out.println( "===========" );
			
			IndexReader reader = IndexReader.open( "c:\\index" );
			
			//删除前文档数量
			System.out.println( "删除前Document数量" + reader.numDocs());
			//删除
			Term term = new Term( "ID", "90" );
			reader.deleteDocuments(term);
			reader.close();
			
			//删除后优化前Document数量
			reader = IndexReader.open( "c:\\index" );
			System.out.println( "删除后优化前Document数量:" + reader.numDocs() );
			reader.close();
			
			//优化
			writer = new IndexWriter( "c:\\index\\", AnalysisFactory.getIndexingAnalisis(), false );
			writer.optimize();
			writer.close();
			
			//优化后Document数量
			reader = IndexReader.open( "c:\\index" );
			System.out.println( "优化后Document数量" + reader.numDocs() );
			for( int i = 0; i < reader.numDocs(); i ++ ) {
				System.out.println( reader.document( i ) );
			}
			reader.close();
			
			//添加一个Document
			writer = new IndexWriter( "c:\\index\\", AnalysisFactory.getIndexingAnalisis(), false );
			writer.addDocument(doc1);
			writer.close();
			
			//添加后Document文档数量
			reader = IndexReader.open( "c:\\index\\" );
			System.out.println( "添加后Document文档数量" + reader.numDocs() );
			reader.close();
			
			
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
	}

}
