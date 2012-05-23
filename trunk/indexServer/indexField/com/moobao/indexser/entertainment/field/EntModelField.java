
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心手机型号
 * @author liuxueyong
 */
public class EntModelField {
	
	public static String fieldName = "entModel";
	/**
	 * @param entModel
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entModel ){
		Field field = new Field( fieldName, entModel, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}	
}
