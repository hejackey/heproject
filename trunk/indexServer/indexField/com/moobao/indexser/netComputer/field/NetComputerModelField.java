package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本型号(例:n73)
 * @author liuxueyong
 */
public class NetComputerModelField {
	public static String fieldName = "netComputerModel";
	/**
	 * @param model
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String model ) {
		Field field = new Field( fieldName, model, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
