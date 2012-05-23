package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机品牌
 * @author liuxueyong
 */
public class PhoneBrandField {
	
	public static String fieldName = "phoneBrand";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String brand ) {
		Field field = new Field( fieldName, brand, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
