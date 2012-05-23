package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机的市场价
 * @author liuxueyong
 */
public class PhonePriceMarketField {
	
	public static String fieldName = "phoneMarketPrice";
	/**
	 * @param market
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String market ) {
		Field field = new Field( fieldName, market, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
