
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 增值辅助分类名称
 * @author liuxueyong
 */
public class EntSlaveNameField {
	
	public static String fieldName = "entSlaveName";
	/**
	 * @param entSlaveName
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entSlaveName ){
		Field field = new Field( fieldName, entSlaveName, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
