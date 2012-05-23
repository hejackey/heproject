package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;


/**
 * 获取文章的来源网址
 * @author liuxueyong
 */
public class ArticleSourceUrlField {
	
	public static String fieldName = "articleSourceUrl";
	/**
	 * @param sourceUrl
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String sourceUrl ) {
		Field field = new Field( fieldName, sourceUrl, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
