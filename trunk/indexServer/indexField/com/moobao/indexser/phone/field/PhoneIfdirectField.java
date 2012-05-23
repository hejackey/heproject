package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机是否主推
 * @author liuxueyong
 */
public class PhoneIfdirectField {
	
	public static String fieldName = "ifdirect";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String ifdirect ) {
		Field field = new Field( fieldName, ifdirect, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
