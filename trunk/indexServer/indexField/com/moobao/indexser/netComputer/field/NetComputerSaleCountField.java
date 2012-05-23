
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本销量.
 * @author liuxueyong
 */
public class NetComputerSaleCountField {
	
	public static String fieldName = "netComputerSaleCount";
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
