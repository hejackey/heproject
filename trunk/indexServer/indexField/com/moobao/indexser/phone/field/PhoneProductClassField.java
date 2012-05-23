package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机(eg.诺基亚:102)
 * @author liuxueyong
 */
public class PhoneProductClassField {
	
	public static String fieldName = "productClass";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String productClass ) {
		Field field = new Field( fieldName, productClass, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
