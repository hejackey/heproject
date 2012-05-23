package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件的优惠政策.
 * @author liuxueyong
 */
public class PeijianRebateField {
	
	public static String fieldName = "peijianRebate";
	/**
	 * @param String Keywords
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String peijianRebate ){
		Field field = new Field( fieldName, peijianRebate, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}
}
