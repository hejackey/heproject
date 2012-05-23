package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取文章添加日期
 * @author liuxueyong
 */
public class ArticleCreateDTField {
	
	public static String fieldName = "articleCreateDt";
	/**
	 * @param createDate
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String createDate ) {
		Field field = new Field( fieldName, createDate, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
