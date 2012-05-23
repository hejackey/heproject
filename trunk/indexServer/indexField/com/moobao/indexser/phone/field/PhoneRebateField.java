package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机的优惠政策.
 * @author liuxueyong
 */
public class PhoneRebateField {
	
	public static String fieldName = "phoneRebate";
	/**
	 * @param String Keywords
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String phoneRebate ){
		Field field = new Field( fieldName, phoneRebate, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}
}
