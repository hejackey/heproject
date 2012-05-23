package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取文章类型代码
 * @author liuxueyong
 */
public class ArticleTypeIDField {
	
	public static String fieldName = "articleTypeID";
	/**
	 * @param tid
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String tid ) {
		Field field = new Field( fieldName, tid, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
