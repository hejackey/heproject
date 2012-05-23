
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 下载量
 * @author liuxueyong
 */
public class EntDownCountField {
	
	public static String fieldName = "entDownCount";
	/**
	 * @param entDownCount
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entDownCount ){
		Field field = new Field( fieldName, entDownCount, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
