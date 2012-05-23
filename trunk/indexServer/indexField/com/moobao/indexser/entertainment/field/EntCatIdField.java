
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 增值主分类(114--手机玩的,115--手机看的,116--手机读的,117--手机听的)
 * @author liuxueyong
 */
public class EntCatIdField {
	
	public static String fieldName = "entCatID";
	/**
	 * @param entCatID
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entCatID ){
		Field field = new Field( fieldName, entCatID, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
