package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机无货颜色
 * @author liuxueyong
 */
public class PhoneColorNoField {
	
	public static String fieldName = "phoneColorNo";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String color ) {
		Field field = new Field( fieldName, color, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
