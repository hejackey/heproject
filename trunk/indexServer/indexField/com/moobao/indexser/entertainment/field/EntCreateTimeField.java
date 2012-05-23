
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 创建时间
 * @author liuxueyong
 */
public class EntCreateTimeField {
	
	public static String fieldName = "entCreateTime";
	/**
	 * @param entCreateTime
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entCreateTime ) {
		Field field = new Field( fieldName, entCreateTime, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
