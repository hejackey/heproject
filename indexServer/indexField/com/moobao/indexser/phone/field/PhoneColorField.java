package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机颜色
 * @author liuxueyong
 */
public class PhoneColorField {
	
	public static String fieldName = "phoneColor";
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
