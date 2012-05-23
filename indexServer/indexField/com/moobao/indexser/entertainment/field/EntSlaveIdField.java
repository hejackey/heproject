
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 增值辅助分类
 * @author liuxueyong
 */
public class EntSlaveIdField {
	
	public static String fieldName = "entSlaveID";
	/**
	 * @param entSlaveID
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entSlaveID ){
		Field field = new Field( fieldName, entSlaveID, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
