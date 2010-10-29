package com.jackey.lucene.test;

import java.io.IOException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;

import com.jackey.lucene.index.AlexaIndexServer;
import com.jackey.lucene.index.BaseIndexServer;

public class TestAlexaIndexServer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		BaseIndexServer indexServer = new AlexaIndexServer();
		IndexWriter writer = indexServer.getIndexWriter();
		indexServer.createIndex(writer);
		
		indexServer.searchIndex("title", "»À»ÀÕ¯");		
	}

}
