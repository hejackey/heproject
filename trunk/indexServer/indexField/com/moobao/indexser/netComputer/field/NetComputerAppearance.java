package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本外观
 * @author liuxueyong
 */
public class NetComputerAppearance {
	
	public static String fieldName = "netcomputerAppearance";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String appearance ) {
		Field field = new Field( fieldName, appearance, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
