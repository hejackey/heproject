
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心资源名称
 * @author liuxueyong
 */
public class EntResNameField {
	
	public static String fieldName = "entResName";
	public static float weight = 1.5f;
	/**
	 * @param entResName
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entResName ){
		Field field = new Field( fieldName, entResName, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}	
}
