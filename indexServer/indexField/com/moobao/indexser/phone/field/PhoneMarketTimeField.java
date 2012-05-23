package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机的上市时间.
 * @author liuxueyong
 */
public class PhoneMarketTimeField {
	
	public static String fieldName = "phoneMarketTime";
	/**
	 * @param String marketTime
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String marketTime ){
		Field field = new Field( fieldName, marketTime, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}
}
