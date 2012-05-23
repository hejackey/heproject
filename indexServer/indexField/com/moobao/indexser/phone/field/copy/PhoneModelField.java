package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机型号(例:n73)
 * @author liuxueyong
 */
public class PhoneModelField {
	public static String fieldName = "phoneModel";
	/**
	 * @param model
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String model ) {
		Field field = new Field( fieldName, model, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
