package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机品牌(eg.102002 物理分类表)
 * @author liuxueyong
 */
public class PhoneBrandCodeField {
	
	public static String fieldName = "phoneBrandCode";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String brandCode ) {
		Field field = new Field( fieldName, brandCode, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
