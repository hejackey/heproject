
package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机对应型号的最低咪啦价(用于排序,所以不需要统一长度)
 * @author liuxueyong
 */
public class PhoneNetLowPriceSortField {
	
	public static String fieldName = "phoneNetLowSortPrice";
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
