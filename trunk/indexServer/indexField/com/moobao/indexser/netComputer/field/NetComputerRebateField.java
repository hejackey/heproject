package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机的优惠政策.
 * @author liuxueyong
 */
public class NetComputerRebateField {
	
	public static String fieldName = "netComputerRebate";
	/**
	 * @param String Keywords
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String rebate ){
		Field field = new Field( fieldName, rebate, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}
}
