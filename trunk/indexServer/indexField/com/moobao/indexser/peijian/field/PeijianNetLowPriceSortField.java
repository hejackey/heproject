
package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件的最低咪啦价(用于排序,所以不需要统一长度)
 * @author liuxueyong
 */
public class PeijianNetLowPriceSortField {
	
	public static String fieldName = "peijianNetLowSortPrice";
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
