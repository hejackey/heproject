package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机操作系统
 * @author liuxueyong
 */
public class PhoneOSField {
	
	public static String fieldName = "PhoneOS";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String type ) {
		Field field = new Field( fieldName, type, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
