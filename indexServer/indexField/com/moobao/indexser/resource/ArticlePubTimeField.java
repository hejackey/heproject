package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取文章发表日期
 * @author liuxueyong
 */
public class ArticlePubTimeField {
	
	public static String fieldName = "articlePubTime";
	/**
	 * @param pubTime
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String pubTime ) {
		Field field = new Field( fieldName, pubTime, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
