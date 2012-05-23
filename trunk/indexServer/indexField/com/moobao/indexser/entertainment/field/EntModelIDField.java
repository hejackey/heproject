
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心手机品牌的ID号
 * @author liuxueyong
 */
public class EntModelIDField {
	
	public static String fieldName = "entModelId";
	/**
	 * @param entModelId
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entModelId ){
		Field field = new Field( fieldName, entModelId, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
