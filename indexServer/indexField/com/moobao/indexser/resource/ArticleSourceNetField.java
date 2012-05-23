package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取来源网站名称
 * @author liuxueyong
 *
 */
public class ArticleSourceNetField {
	
	public static String fieldName = "SourceName";
	/**
	 * @param name
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String name ) {
		Field field = new Field( fieldName, name, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
