package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 手机功能(32)
 * @author liuxueyong
 */
public class PhoneFunctionIntField {
	
	public static String fieldName = "phoneFunction";
	/**
	 * @param describe
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String func ) {
		Field field = new Field( fieldName, func, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
