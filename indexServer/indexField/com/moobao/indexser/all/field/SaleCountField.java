
package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机销量.
 * @author liuxueyong
 */
public class SaleCountField {
	
	public static String fieldName = "saleCount";
	/**
	 * @param saleCount
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String saleCount ){
		Field field = new Field( fieldName, saleCount, Field.Store.YES, Field.Index.NO );
		return field ;
	}	
}
