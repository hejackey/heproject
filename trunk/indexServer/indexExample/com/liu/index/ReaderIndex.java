package com.liu.index;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;

public class ReaderIndex {

	private static IndexReader reader = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//手机
//		System.out.println( "==================手机文档=================" );
//		try {
//			reader = IndexReader.open( "E:\\index\\phoneIndex" );
//			Document doc = new Document();
//			for( int i = 0; i < reader.numDocs(); i ++ ) {
//			    doc = reader.document( i );
//				System.out.println( doc );
//				System.out.println( "名称:" + doc.get( "phoneName" ) );
//				System.out.println( "品牌:" + doc.get( "phoneBrand" ) );
//				System.out.println( "屏幕尺寸:" + doc.get( "screenSize" ) );
//				System.out.println( "摄像头像素:" + doc.get( "CameraPixel" ) );
//				System.out.println( "上市时间:" + doc.get( "phoneMarketTime" ) );
//			}
//			System.out.println( "Document总数:" + reader.numDocs() );
//		}
//		catch( IOException e ) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				reader.close();
//			}
//			catch( IOException ex ) {
//				ex.printStackTrace();
//			}
//		}
		
//		//配件
//		System.out.println( "==================配件文档=================" );
//		try {
//			reader = IndexReader.open( "E:\\index\\peiJianIndex" );
//			System.out.println( reader.numDocs() );
//			for( int i = 0; i < reader.numDocs(); i ++ ) {
//				Document doc = reader.document( i );
//				System.out.println( doc );
//				System.out.println( "名称:" + doc.get( "peiJianName" ) );
//				System.out.println( "品牌:" + doc.get( "peiJianDescribe" ) );
//				System.out.println( "content:" + doc.get( "PeiJianSearchField" ) );
//			}
//			System.out.println( "Document总数:" + reader.numDocs() );
//		}
//		catch( IOException e ) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				reader.close();
//			}
//			catch( IOException ex ) {
//				ex.printStackTrace();
//			}
//		}
		//资讯
		System.out.println( "==================资讯文档=================" );
		try {
			reader = IndexReader.open( "E:\\index\\resourceIndex" );
			Document doc = new Document();
			for( int i = 0; i < reader.numDocs(); i ++ ) {
				doc = reader.document( i );
				System.out.println( doc );
				System.out.println( doc.get("articleTitle") );
				System.out.println( doc.get("articleContent").trim());
				System.out.println( doc.get("articlePubTime").trim());
			}
			System.out.println( reader.numDocs() );
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch( IOException ex ) {
				ex.printStackTrace();
			}
		}
		
//		System.out.println( "==================娱乐中心=================" );
//		try {
//			reader = IndexReader.open( "E:\\index\\entertainmentIndex" );
//			System.out.println( reader.numDocs() );
//			for( int i = 0; i < reader.numDocs(); i ++ ) {
//				System.out.println( reader.document( i ) );
//			}
//			System.out.println( "Document总数:" + reader.numDocs() );
//		}
//		catch( IOException e ) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				reader.close();
//			}
//			catch( IOException ex ) {
//				ex.printStackTrace();
//			}
//		}
	}
}
