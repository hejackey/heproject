package com.moobao.searchlist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.indexser.peijian.field.PeiJianContentField;
import com.moobao.indexser.peijian.field.PeiJianNameField;
import com.moobao.indexser.phone.field.PhoneContentField;
import com.moobao.indexser.phone.field.PhoneKeywordsField;
import com.moobao.indexser.peijian.field.PeiJianKeywordsField;
import com.moobao.indexser.phone.field.PhoneNameField;
import com.moobao.searchInterface.SearchInterface;

public class GetKeywordNum {
	// 获取分词工具.
	private static MMAnalyzer analyzer = AnalysisFactory.getSearchingAnalisis();

	/**
	 * 得到搜索列表
	 * 
	 * @param query
	 *            搜索query
	 * @param num
	 *            返回数量
	 * @return List
	 */
	public static List getSearchNum(int type, String fieldName,
			IndexSearcher searcher, List<String> list_keywords) {

		// List<String> list = new ArrayList<String>(); //名称#数量
		List<Integer> listNum = new ArrayList<Integer>(); // 关键词数量

		QueryParser parser = new QueryParser(fieldName, analyzer);
		parser.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query query = null;
		Hits hits = null;
		Term term = null;
		String key = "";
		String word = "";
		if (list_keywords != null && list_keywords.size() > 0) {
			for (int i = 0; i < list_keywords.size(); i++) {
				// term = new Term( fieldName, list_keywords.get(i).trim() );
				// PrefixQuery preQuery = new PrefixQuery( term );
				//				
				// try {
				// hits = searcher.search( preQuery );
				// listNum.add( hits.length() );
				// } catch (IOException e) {
				// System.err.print( "获取数量异常!" );
				// }
				word = list_keywords.get(i).trim();
				if (word != null && !word.equals("") && !word.equals("as")
						&& !word.equals("a")) {
					try {
						key = analyzer.segment(word, " ").trim();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					try {
						if( !key.equals("") ) {
							query = parser.parse(key);
							hits = searcher.search(query);
							listNum.add(hits.length());
						}
						else {
							listNum.add(0);
						}
							
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					listNum.add(0);
				}
			}
		}

		return listNum;
	}

	/**
	 * 得到某关键词的搜索个数
	 * 
	 * @param query
	 *            搜索query
	 * @param num
	 *            返回数量
	 * @return int
	 */
	public static int getSearchNum(int type, 
			IndexSearcher searcher, String keywords) {
		
		String contentField = "", nameField = "", keywordField = "";
		if( type == 1 ) {
			contentField = PhoneContentField.fieldName;
			nameField = PhoneNameField.fieldName;
			keywordField = PhoneKeywordsField.fieldName;
		}
		else {
			if( type == 2 ) {
				contentField = PeiJianContentField.fieldName;
				nameField = PeiJianNameField.fieldName;
				keywordField = PeiJianKeywordsField.fieldName;
			}
		}
		
		Query query1 = null, query2 = null, query3 = null;
		Hits hits = null;
		int sum = 0;
		if (keywords != null && !keywords.trim().equals("") && keywords.length() > 0 ) {
			try {
				String key = analyzer.segment(keywords, " ").trim();

				if (key == null || key.equals("")) {
					sum = 0;
				} else {
					BooleanQuery bquery = new BooleanQuery();
					// 搜索(参数:查询Field,分词器).
					QueryParser parser1 = new QueryParser(
							contentField, analyzer);
					parser1.setDefaultOperator(QueryParser.AND_OPERATOR);

					QueryParser parser2 = new QueryParser(
							nameField, analyzer);
					parser2.setDefaultOperator(QueryParser.AND_OPERATOR);
					
					QueryParser parser3 = new QueryParser(
							keywordField, analyzer);
					parser3.setDefaultOperator(QueryParser.AND_OPERATOR);

					try {
						query1 = parser1.parse(key);
						query2 = parser2.parse(key);
						query3 = parser3.parse(key);
						bquery.add(query1, BooleanClause.Occur.SHOULD);
						bquery.add(query2, BooleanClause.Occur.SHOULD);
						bquery.add(query3, BooleanClause.Occur.SHOULD);

						hits = searcher.search(bquery);
						sum = hits.length();
						//System.out.println(sum);

					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException ex) {

			}

		}
		return sum;
	}
}
