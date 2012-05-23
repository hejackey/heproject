package com.moobao.indexProcessOptimize;

import org.apache.lucene.index.IndexWriter;

import com.moobao.config.PropertyConfiguration;

public class SetOptimize {
	
	private static final String mergeFactor = PropertyConfiguration.setMergeFactor();
	private static final String maxMergeDocs = PropertyConfiguration.setMaxMergeDocs();
	private static final String maxBufferedDocs = PropertyConfiguration.setMaxBufferedDocs();
	
	public static IndexWriter setParameter( IndexWriter writer ) {
		
		writer.setMergeFactor( Integer.parseInt( mergeFactor ) ); 
		writer.setMaxMergeDocs( Integer.parseInt( maxMergeDocs ) );         //每个segment的最大document数.
		writer.setMaxBufferedDocs( Integer.parseInt( maxBufferedDocs ) );   //内存中最大document数量.
		
		return writer;
	}
}
