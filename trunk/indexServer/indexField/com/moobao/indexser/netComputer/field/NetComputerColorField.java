package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本颜色
 * @author liuxueyong
 */
public class NetComputerColorField {
	
	public static String fieldName = "netComputerColor";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String color ) {
		Field field = new Field( fieldName, color, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
