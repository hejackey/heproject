package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机的下架时间.
 * @author liuxueyong
 */
public class PhoneDownTimeField {
	
	public static String fieldName = "phoneDownTime";
	/**
	 * 手机的下架时间
	 * @param String
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String DownTime ){
		Field field = new Field( fieldName, DownTime, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
