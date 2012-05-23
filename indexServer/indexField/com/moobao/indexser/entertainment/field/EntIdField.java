
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心ID
 * @author liuxueyong
 */
public class EntIdField {
	
	public static String fieldName = "entID";
	/**
	 * @param entID
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entID ){
		Field field = new Field( fieldName, entID, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
