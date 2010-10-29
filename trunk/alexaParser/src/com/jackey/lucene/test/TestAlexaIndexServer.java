package com.jackey.lucene.test;

import org.apache.lucene.index.IndexWriter;

import com.jackey.lucene.index.AlexaIndexServer;
import com.jackey.lucene.index.BaseIndexServer;

public class TestAlexaIndexServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BaseIndexServer indexServer = new AlexaIndexServer();
		IndexWriter writer = indexServer.getIndexWriter();
		indexServer.createIndex(writer);
	}

}
