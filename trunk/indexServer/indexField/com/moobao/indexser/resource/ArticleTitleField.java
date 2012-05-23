package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取文章标题
 * @author liuxueyong
 */
public class ArticleTitleField {
	
	public static String fieldName = "articleTitle";
	public static float weight = 1.5f;
	/**
	 * @param title
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String title ) {
		Field field = new Field( fieldName, title, Field.Store.YES, Field.Index.TOKENIZED );
		field.setBoost( weight );
		return field;
	}
}
