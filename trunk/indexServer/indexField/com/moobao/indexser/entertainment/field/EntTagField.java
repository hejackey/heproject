
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心"前台标签"
 * @author liuxueyong
 */
public class EntTagField {
	
	public static String fieldName = "entTag";
	/**
	 * @param entTag
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entTag ){
		Field field = new Field( fieldName, entTag, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}	
}
