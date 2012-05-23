
package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件销量.
 * @author liuxueyong
 */
public class PeiJianSaleCountField {
	
	public static String fieldName = "peiJianSaleCount";
	/**
	 * @param saleCount
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String saleCount ){
		Field field = new Field( fieldName, saleCount, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
