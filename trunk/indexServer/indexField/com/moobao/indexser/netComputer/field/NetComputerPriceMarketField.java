package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本的市场价
 * @author liuxueyong
 */
public class NetComputerPriceMarketField {
	
	public static String fieldName = "netComputerMarketPrice" ;
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
