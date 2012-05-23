
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 最后更新时间
 * @author liuxueyong
 */
public class EntLastOperTimeField {
	
	public static String fieldName = "entLastOperTime";
	/**
	 * @param entLastOperTime
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entLastOperTime ) {
		Field field = new Field( fieldName, entLastOperTime, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
