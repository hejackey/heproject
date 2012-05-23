
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机对应型号的最低咪啦价(用于排序,所以不需要统一长度)
 * @author liuxueyong
 */
public class NetComputerNetLowPriceSortField {
	
	public static String fieldName = "netcomputerNetLowSortPrice";
	/**
	 * @param netPrice
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String netPrice ){
		Field field = new Field( fieldName, netPrice, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
