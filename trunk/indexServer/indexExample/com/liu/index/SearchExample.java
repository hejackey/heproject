package com.liu.index;

import java.io.IOException;
import java.util.Date;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.config.PropertyConfiguration;

public class SearchExample {
    
	private String indexPath = "";
	private IndexWriter writer = null;
	private Analyzer analyzer = null;
	private String dictionary_file = PropertyConfiguration.getWordDictionary();
	private static String INDEX_STORE_PATH = "E:\\index1";
	
	public static Document buildProductDocument(  String ID ) {

		Document doc = new Document();

		Field identifier = new Field("PRODUCT_ID", ID, Field.Store.YES,
				Field.Index.UN_TOKENIZED);

		long mills = System.currentTimeMillis();
		Field indextime = new Field("INDEX_TIME", mills + "", Field.Store.YES,
				Field.Index.UN_TOKENIZED);

		Field producturl = new Field("PRODUCT_URL", "http://www.5366.com",
				Field.Store.YES, Field.Index.UN_TOKENIZED);

		Field name = new Field("PRODUCT_NAME", "主要功能: N95内置天线,手机时钟,内置震动,情景模式,通话时间提示,免提通话,待机图片,动画屏保功能,来电图片识别,来电铃声识别,飞行模式 附加功能: 闹钟,关机闹钟,日历,计算器,日程表,记事本,备忘录,语音备忘录,世界时钟,货币换算,单位换算,定时器,秒表",
				Field.Store.YES, Field.Index.TOKENIZED);

		Field type = new Field("PRODUCT_TYPE", "N73",
				Field.Store.YES, Field.Index.TOKENIZED);
		
		String text = "诺基亚";
		text += " " + "N73";
		Field all = new Field("all", text, Field.Store.YES, Field.Index.TOKENIZED);

		// add all
		doc.add(identifier);
		doc.add(indextime);
		doc.add(producturl);
		doc.add(name);
		doc.add(type);
		doc.add(all);
		//System.out.println( doc );

		return doc;

	}

	public SearchExample(String indexPath) throws Exception {
		this.indexPath = indexPath;
		initialize();
	}

	/**
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		writer = new IndexWriter(indexPath, AnalysisFactory.getSearchingAnalisis(), true);
	}
	
	public void close() {
		try{
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
			writer = null;
		}
	}
	
	public void addProduct() throws Exception {
		writer.setUseCompoundFile(true);// 采用复合索引.
		writer.addDocument(SearchExample.buildProductDocument("12"));
		writer.addDocument(SearchExample.buildProductDocument("2"));
	}
	
	public void optimizeIndex() throws Exception {
		writer.optimize();
	}
	
	
	
	public static void main( String[] args ) {
		try {
			SearchExample see = new SearchExample("E:\\index1");
			see.addProduct();
			see.optimizeIndex();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
		
		//����
		try {
			String searchStr = "手机单位";
			//create a IndexSearcher Object
			IndexSearcher searcher = new IndexSearcher( INDEX_STORE_PATH ) ;
			
			Date time_start = new Date();
			
			MMAnalyzer analyser = new MMAnalyzer();
			String content = analyser.segment( "三星E498红色手机可更换彩壳的翻盖手机，立体声双喇叭设计，支持MP3等多种格式的音频播放，130万像素照相机，10级亮度，支持视频摄录功能，彩色拨号字体，多种输入法选择，1000条联系人存储空间，Google搜索多彩的可更换外壳、立体声双喇叭", " ");
			String str = analyser.segment( searchStr, " " );
			
			System.out.println( content );
			
			
			//create two Term Object.
			//Term t1 = new Term( "PRODUCT_NAME", str );
			//Term t2 = new Term( "author", "hatcher" );
			
			//create two TermQuery Object.
			//TermQuery q2 = new TermQuery( t2 );
			
			//create PhraseQuery Object
			//PhraseQuery query = new PhraseQuery();
			//add the TermQueries to PhraseQuery.
			//query.add( t1 );
			//query.add( t2 );
			
			//参数1:指定在哪个Field搜索,参数2:分词器.
			QueryParser parser = new QueryParser( "PRODUCT_NAME", AnalysisFactory.getSearchingAnalisis() );
			parser.setDefaultOperator(QueryParser.AND_OPERATOR);
			
			Sort sort = new Sort();
			//SortField f= new SortField("PRODUCT_ID", false);����
			SortField f = new SortField( "PRODUCT_ID", SortField.INT, true ); 
			
			sort.setSort( f );
			Query query = null;
			
			try {
				query = parser.parse( str );
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Date time_end = new Date();
			
			System.out.println( query.toString() );
			
			//search			
			Hits hits = searcher.search( query, sort );
			for( int j = 0; j < hits.length(); j ++ ) {
				Document doc = hits.doc( j );
				System.out.println( "ID:" + doc.get( "PRODUCT_ID" ) );
				System.out.println( "产品名称:" + doc.get( "PRODUCT_NAME" ) );
//				System.out.println( "����:" + doc.get( "author" ) );
//				System.out.println( "�����:" + doc.get( "publisher" ) );
//				System.out.println( "�������:" + doc.get( "pubDate" ) );
//				System.out.println( "�۸�:" + doc.get( "price" ) );
//				System.out.println( "����:" + doc.get( "desc" ) );
//				System.out.println( "------------------------------------" );
			}
			long time = time_end.getTime() - time_start.getTime();
			
			System.out.println( "用时:" + time + "ms" );
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
