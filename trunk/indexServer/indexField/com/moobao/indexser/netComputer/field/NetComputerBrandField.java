package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本品牌(eg.诺基亚)
 * @author liuxueyong
 */
public class NetComputerBrandField {
	
	public static String fieldName = "netComputerBrand";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String brand ) {
		Field field = new Field( fieldName, brand, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
