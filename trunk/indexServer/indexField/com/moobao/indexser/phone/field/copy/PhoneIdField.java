
package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机ID
 * @author liuxueyong
 */
public class PhoneIdField {
	
	public static String fieldName = "phoneId";
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
