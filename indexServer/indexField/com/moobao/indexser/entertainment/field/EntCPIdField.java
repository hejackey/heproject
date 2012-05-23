
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心制造商ID
 * @author liuxueyong
 */
public class EntCPIdField {
	
	public static String fieldName = "entCPID";
	/**
	 * @param cpID
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String cpID ){
		Field field = new Field( fieldName, cpID, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
