
package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取从表中要推荐的手机ID
 * @author liuxueyong
 */
public class IdField {
	
	public static String fieldName = "id";
	/**
	 * @param pid
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String pid ){
		Field field = new Field( fieldName, pid, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
