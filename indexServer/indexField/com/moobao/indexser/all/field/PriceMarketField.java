package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机的市场价
 * @author liuxueyong
 */
public class PriceMarketField {
	
	public static String fieldName = "marketPrice";
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
