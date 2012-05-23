package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件的市场价
 * @author liuxueyong
 */
public class PeiJianPriceMarketField {
	
	public static String fieldName = "peiJianMarketPrice" ;
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
