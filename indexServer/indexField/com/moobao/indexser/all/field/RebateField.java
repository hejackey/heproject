package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机的优惠政策.
 * @author liuxueyong
 */
public class RebateField {
	
	public static String fieldName = "rebate";
	/**
	 * @param String Keywords
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String rebate ){
		Field field = new Field( fieldName, rebate, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}
}
