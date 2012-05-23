package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机型号(例:n73)
 * @author liuxueyong
 */
public class ModelField {
	public static String fieldName = "model";
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
