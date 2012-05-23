package com.liu.test;

import java.io.IOException;
import java.util.Date;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RangeQuery;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.config.PropertyConfiguration;

public class MultiFieldSearch {

	private static String INDEX_PATH = PropertyConfiguration
			.getPhoneIndexPath();
	private static Analyzer analyzer = AnalysisFactory.getSearchingAnalisis();

	public static void multiSearchPhone() {

		IndexSearcher searcher = null;
		Query q = null;
		
		try {
			searcher = new IndexSearcher(INDEX_PATH);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		Date time_start = new Date();

		try {
			//MMAnalyzer analyser = new MMAnalyzer();
			
			//查询关键字.
			String query1 = "三星 诺基亚";
			String query2 = "GSM";
			//String query3 = "[100 to 200]";
			
			Term camera1 = new Term("CameraPixel", "100");    
			Term camera2   = new Term("CameraPixel","200");    
			Query qq = new RangeQuery(camera1,camera2,true);
			//System.out.println( query.toString() );
			String query3 = qq.toString();
			
			Term screen1 = new Term("screenSize", "2.0");    
			Term screen2   = new Term("screenSize","2.5");    
			Query qqq = new RangeQuery(screen1,screen2,true);
			//System.out.println( query.toString() );
			String query4 = qqq.toString();
			String query5 = "直板 翻盖";
			
			
			String[] queries = {query1, query2, query3, query4, query5};

			//Field名称
			String field1 = "phoneBrand";
			String field2 = "netWorkType";
			String field3 = "CameraPixel";
			String field4 = "screenSize";
			String field5 = "phoneAppearance";
			String[] fields = {field1, field2, field3, field4, field5};
			
			//查询子句关系
			BooleanClause.Occur [] clauses = { BooleanClause.Occur.MUST, BooleanClause.Occur.MUST, BooleanClause.Occur.MUST, BooleanClause.Occur.MUST, BooleanClause.Occur.MUST };
			
			try {
				q = MultiFieldQueryParser.parse(queries, fields, clauses, (MMAnalyzer)analyzer);
				System.out.println( q.toString() );
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			Sort sort = new Sort();
			// 按(销量phoneSaleCount,上架时间phoneUpTime或价格phoneNetSortPrice)排序.
			SortField f = new SortField("phoneUpTime", SortField.INT, false);
			sort.setSort(f);

			Hits hits = searcher.search( q, sort );
			for (int j = 0; j < hits.length(); j++) {
				Document doc = hits.doc(j);
				System.out.println("ID:" + doc.get("phoneId"));
				System.out.println( "名称:" + doc.get("phoneName") );
				System.out.println( "简单描述:" + doc.get("phoneDescribe") );
				System.out.println( "推荐语:" + doc.get("phonePromotion") );
				System.out.println( "市场价:" + doc.get("phoneMarketPrice") );
				System.out.println( "摩宝价:" + doc.get("phoneNetSortPrice") );
				System.out.println("上架时间:" + doc.get("phoneUpTime"));
				System.out.println("Brand:" + doc.get("phoneBrand") );
				System.out.println("网络类型:" + doc.get("netWorkType") );
				System.out.println("摄像头像素:" + doc.get("CameraPixel") );
				System.out.println("屏幕尺寸:" + doc.get("screenSize") );
				System.out.println("外观:" + doc.get("phoneAppearance") );
				System.out.println("功能:" + doc.get("phoneFunction") );
			}
			Date time_end = new Date();
			long time = time_end.getTime() - time_start.getTime();

			System.out.println("用时:" + time + "ms");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MultiFieldSearch.multiSearchPhone();
	}
}
