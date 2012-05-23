
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心状态：（0－无效; 1－有效）
 * @author liuxueyong
 */
public class EntStatusField {
	
	public static String fieldName = "entStatus";
	/**
	 * @param entStatus
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entStatus ){
		Field field = new Field( fieldName, entStatus, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
