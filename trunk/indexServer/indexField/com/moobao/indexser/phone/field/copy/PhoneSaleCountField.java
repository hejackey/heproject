
package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机销量.
 * @author liuxueyong
 */
public class PhoneSaleCountField {
	
	public static String fieldName = "phoneSaleCount";
	/**
	 * @param saleCount
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String saleCount ){
		Field field = new Field( fieldName, saleCount, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
