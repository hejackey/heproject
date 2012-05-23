package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机品牌(eg.诺基亚:102002)
 * @author liuxueyong
 */
public class PhoneBrandVField {
	
	public static String fieldName = "phoneBrandV";
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
