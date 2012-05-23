package com.moobao.indexser;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * 手机索引的添加,删除与更新接口.
 * @author liuxueyong
 */
public class PhoneIndexser {
	
	IndexWriter writer = null;
	IndexReader reader = null;
	
	//得到写索引和修改索引对象
	public PhoneIndexser( IndexWriter writer ) {
		this.writer = writer;
	}
	
	//得到修改索引对象
	public PhoneIndexser( IndexReader reader) {
		this.reader = reader;
	}
	
	
	/**
	 * 向索引中增加一个Document
	 * @param doc
	 * @exception IOException
	 * @return null
	 */
	public void indexADD(Document doc) {
		try {
			writer.addDocument(doc);
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
    }
    
	/**
	 * 以List为单位向索引中增加Document
	 * @param listdoc
	 * @exception IOException
	 * @return null
	 */
    public void indexADD(List<Document> listdoc) {
		for( int i = 0; i < listdoc.size(); i ++ ) {
			try {
				writer.addDocument( listdoc.get( i ) );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    /**
     * 索引目录合并
     * @param dir
     * @exception IOException
     * @return null
     */
    public void indexDirADD(Directory dir) {
    	//初始化一个FSDirectory对象, 不清空原目录
    	try {
    		FSDirectory fsDir = FSDirectory.getDirectory( dir.toString(), false );
    		writer.addIndexes( new Directory[] {fsDir} );
    	}
    	catch( IOException e ) {
    		e.printStackTrace();
    		System.err.println( "索引目录合并失败!");
    	}
    }
    
    /**
     * 多目录索引合并
     * @param listDir
     * @exception IOException
     * @return null
     */
    public void indexDirADD(List<Directory> listDir){
    	try {
    		for( int i = 0; i < listDir.size(); i ++ ) {
				writer.addIndexes( new Directory[] {listDir.get( i )} );
			}
    	}
    	catch( IOException e ) {
    		e.printStackTrace();
    		System.err.println( "多目录索引合并失败!" );
    	}
    }
    
    /**
     * 从索引文件中删除一个Document
     * @param doc
     * @exception IOException
     * @return null
     */
    public void indexDel(Document doc) {
    	String productID = doc.get( "phonePrimaryId" );
    	try {
    		//删除Document
    		Term term = new Term( "phonePrimaryId", productID );
        	reader.deleteDocuments(term);
    	}
    	catch( IOException e ) {
    		e.printStackTrace();
    		System.err.println( "删除文档失败!" );
    	}
    }
    
    /**
     * 从索引文件中删除一个Document链表
     * @param listdoc
     * @exception IOException
     * @return null
     */
    public void indexDel(List<Document> listdoc) {
    	String productID = "";
    	Term term = null;
    	try {
    		//删除Document列表
    		for( int i = 0; i < listdoc.size(); i ++ ) {
    			productID = listdoc.get(i).get( "phonePrimaryId" );
    			term = new Term( "phonePrimaryId", productID );
    			reader.deleteDocuments( term );
    		}
    	}
    	catch( IOException e ) {
    		e.printStackTrace();
    		System.err.println( "删除批量文档失败!" );
    	}
    }
    
    /**
     * 从索引文件中删除指定的Document
     * @param keyid
     * @exception IOException
     * @return null
     */
    public void indexDel(String keyid) {
    	try {
    		//删除Document
    		Term term = new Term( "phonePrimaryId", keyid );
        	reader.deleteDocuments(term);
    	}
    	catch( IOException e ) {
    		e.printStackTrace();
    		System.err.println( "删除文档失败!" );
    	}
    }
}
