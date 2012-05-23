package com.moobao.searchlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.search.IndexSearcher;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.apply.b2b.cms.base.BaseDAO;
import com.moobao.indexser.peijian.field.PeiJianContentField;
import com.moobao.indexser.phone.field.PhoneContentField;
import com.moobao.searchInterface.SearchInterface;

/**
 * 将词库的关键字和相应的搜索数目写入表中.
 * @author liuxueyong
 *
 */
public class WriteDbList {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	/**
	 * 将词库中的keywords的搜索数目插入表中
	 * @param type 1:手机,2:配件,3.资讯,4.增值
	 * @param fieldName
	 * @param searcher
	 */
	public void writerToDb( int type, String fieldName, IndexSearcher searcher ) {
		
		Set set1  = new HashSet();
		Set set2  = new HashSet();
		
		//取出表中的.
		GetFieldFromDb gf = new GetFieldFromDb(); 
		String sql1 = "select productname, productmodel from product_list";
		String sql2 = "select keywords from search_keywords";
		
		List<String> fields = new ArrayList();
		fields.add("productname");
		fields.add("productmodel");
		
		set1 = gf.getField(sql1, fields);
		set2 = gf.getField(sql2, "keywords");
		
		List<String> list_keywords = new ArrayList(set1); 
		List<String> list_keywords2 = new ArrayList(set2);
		
		String product = "";
		for( int i = 0; i < list_keywords.size(); i ++ ) {
			product = list_keywords.get(i);
			for( int j = 0; j < list_keywords2.size(); j ++ ) {
				if(list_keywords2.get(j).equals(product)) {
					list_keywords.remove(i);
					i--;
				}
			}
		}
		
		//词库中的词所对应的数量.
		List<Integer> list_num = GetKeywordNum.getSearchNum( type, fieldName, searcher, list_keywords );
		BaseDAO dao = new BaseDAO();
		String sql = "";
		if( list_num != null && list_num.size() > 0 ) {
			for( int i = 0; i < list_num.size(); i ++ ) {
				sql = "insert into search_keywords(id, search_type, keywords,num, sortby) values( SEQ_SEARCH_KEYWORDS.nextval, '"+type+"', '"+list_keywords.get(i)+"' ,'"+list_num.get(i)+"' , 0)";
				dao.executeUpdate( sql );
			}
		}
	}
	
	/**
	 * 更新search_keywords中"手机"和"配件"的搜索数量
	 * @param type 1:手机,2:配件
	 * @param fieldName
	 * @param searcher
	 */
	public void updateToDb() {
		
		IndexSearcher searcher_phone = SearchInterface.getPhoneSearcher();
		IndexSearcher searcher_peijian = SearchInterface.getPeiJianSearcher();
		
		BaseDAO dao = new BaseDAO();
		List<Integer> search_type = new ArrayList<Integer>();    //1.手机  2.配件
		List<String> list_keywords = new ArrayList<String>();
		List<Integer> list_num = new ArrayList<Integer>();
		
		SqlRowSet rs = dao.getRowSetQuery( "select search_type, keywords from search_keywords" );
		while( rs.next() ) {
			search_type.add( rs.getInt("search_type") );
			list_keywords.add( rs.getString("keywords") );
		}
		
		int num = 0;
		if( search_type != null && search_type.size() > 0 ) {
			for( int i = 0; i < search_type.size(); i ++ ) {
				if( search_type.get(i) == 1 ) {
					//表中keywords所对应的数量.
					num = GetKeywordNum.getSearchNum( search_type.get(i), searcher_phone, list_keywords.get(i) );
				}
				else {
					//表中keywords所对应的数量.
					num = GetKeywordNum.getSearchNum( search_type.get(i), searcher_peijian, list_keywords.get(i) );
				}
				list_num.add( num );
			}
			
			
			//更新表的数量字段
			String sql = "";
			for( int i = 0; i < list_num.size(); i ++ ) {
				sql = "update search_keywords set num = "+list_num.get(i)+" where search_type = '"+search_type.get(i)+ "' and keywords = '"+list_keywords.get(i)+"' ";
				dao.executeUpdate( sql );
			}
		}
		else {
			System.out.println( "没有满足条件的记录!" );
		}
		
		//System.out.println( "search_type数量:" + search_type.size() + " " +  "list_keywords数量:" + list_keywords.size() + " " + "list_num数量:" + list_num.size() );
		
	}
	
	
    public void addHighProperty( List<String> sql ) {

		BaseDAO dao = new BaseDAO();
		if( sql != null && sql.size() > 0 ) {
			for( int i = 0; i < sql.size(); i ++ ) {
				dao.executeUpdate( sql.get(i) );
			}
		}
	}
	
	public static void main( String[] args ) {
		
		WriteDbList db = new WriteDbList();
		int type = 1;
		String fieldName = PhoneContentField.fieldName;
		IndexSearcher searcher = SearchInterface.getPhoneSearcher();
		db.writerToDb( type, fieldName, searcher );
		db.updateToDb();
		
		
		
//		List<String> sql = new ArrayList<String>();
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval,1,'拍照',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'MP3',0,0)");
//	    sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'视频拍摄',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'录音',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'蓝牙',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'扩展卡',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'手写',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'智能系统',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'红外',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'和弦',0,0)");
//		sql.add("insert into search_keywords(id,search_type, keywords, num ,sortby) values( SEQ_SEARCH_KEYWORDS.nextval, 1,'GPS',0,0)");
//		db.addHighProperty(sql);
	}
}
