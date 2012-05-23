package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机主商品ID(主商品表)
 * @author liuxueyong
 */
public class PhonePrimaryId {
	
	public static String fieldName = "phonePrimaryId";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String type ) {
		Field field = new Field( fieldName, type, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
