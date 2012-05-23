package com.liu.test;

import java.io.IOException;
import java.util.Date;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.config.PropertyConfiguration;

public class SearchPhone {

	private static String INDEX_PATH = PropertyConfiguration.getPhoneIndexPath();
	private static Analyzer analyzer = AnalysisFactory.getSearchingAnalisis();
	/**
	 * @param args
	 */
	public static void searchPhone( String parm ) {
		
		String field = "phoneSearchField";
		IndexSearcher searcher = null;
		Query q = null;
		try {
			searcher = new IndexSearcher( INDEX_PATH );
		}
		catch( java.io.IOException e ) {
			e.printStackTrace();
		}
		Date time_start = new Date();
		
		try {
			MMAnalyzer analyser = (MMAnalyzer)analyzer;
			String content = analyser.segment( "三星E498红色手机可更换彩壳的翻盖手机，立体声双喇叭设计，支持MP3等多种格式的音频播放，130万像素照相机，10级亮度，支持视频摄录功能，彩色拨号字体，多种输入法选择，1000条联系人存储空间，Google搜索多彩的可更换外壳、立体声双喇叭", " ");
			System.out.println( content );
			
			String searchQuery = analyser.segment( parm, " " );
			
			QueryParser parser = new QueryParser( field, (MMAnalyzer)analyzer );
			parser.setDefaultOperator( QueryParser.AND_OPERATOR );
			try {
				q = parser.parse( searchQuery );
				System.out.println( q.toString() );
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Sort sort = new Sort();
			//按(销量phoneSaleCount,上架时间phoneUpTime或价格phoneNetSortPrice)排序.
            SortField f = new SortField( "phoneUpTime", SortField.INT, false ); 
			sort.setSort( f );
			
			Hits hits = searcher.search( q, sort );
			for( int j = 0; j < hits.length(); j ++ ) {
				Document doc = hits.doc( j );
				System.out.println( "ID:" + doc.get( "phoneId" ) );
				System.out.println( "产品描述:" + doc.get( "phoneDescribe" ) );
				System.out.println( "上架时间:" + doc.get( "phoneUpTime" ) ); 
			}
			Date time_end = new Date();
			long time = time_end.getTime() - time_start.getTime();
			
			System.out.println( "用时:" + time + "ms" );
		}
		catch( IOException ex ) {
			ex.printStackTrace();
		}		
	}
	
	
	
	public static void main(String[] args) {
		
		SearchPhone.searchPhone( "诺基亚<>@#!$@%#^&[]*|." );
	}
}
