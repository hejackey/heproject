package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取资讯ID
 * @author liuxueyong
 */
public class ArticleIDField {
	
	public static String fieldName = "articleID";
	/**
	 * @param aid
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String aid ) {
		Field field = new Field( fieldName, aid, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
