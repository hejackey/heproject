
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 作者信息
 * @author liuxueyong
 */
public class EntAuthorField {
	
	public static String fieldName = "entAuthor";
	/**
	 * @param entAuthor
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entAuthor ){
		Field field = new Field( fieldName, entAuthor, Field.Store.YES, Field.Index.NO );
		return field;
	}	
}
