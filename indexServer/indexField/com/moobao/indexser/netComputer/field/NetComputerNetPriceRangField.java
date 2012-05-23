
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本的咪啦价(用于范围搜索,所以需要统计整数部分为12位)
 * @author liuxueyong
 */
public class NetComputerNetPriceRangField {
	
	public static String fieldName = "netComputerNetRangPrice";
	/**
	 * @param netPrice
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String netPrice ) {
		Field field = new Field( fieldName, netPrice, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
