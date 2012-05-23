
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心"后台标签"
 * @author liuxueyong
 */
public class EntBackTagField {
	
	public static String fieldName = "entBakcTag";
	/**
	 * @param entBakcTag
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entBakcTag ){
		Field field = new Field( fieldName, entBakcTag, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}	
}
