
package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机的摩宝价(用于范围搜索,所以需要统计整数部分为12位)
 * @author liuxueyong
 */
public class NetPriceRangField {
	
	public static String fieldName = "netRangPrice";
	/**
	 * @param netPrice
	 * @exception no thrown Exception
	 * @return
	 */
	public static Field getField( String netPrice ){
		Field field = new Field( fieldName, netPrice, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
