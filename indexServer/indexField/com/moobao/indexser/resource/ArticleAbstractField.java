package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取文章摘要
 * @author liuxueyong
 *
 */
public class ArticleAbstractField {
	
	public static String fieldName = "ArticleAbstract";
	/**
	 * @param zhaiYao
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String zhaiYao ) {
		Field field = new Field( fieldName, zhaiYao, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
