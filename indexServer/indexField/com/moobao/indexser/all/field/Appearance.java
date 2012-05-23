package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机外观
 * @author liuxueyong
 */
public class Appearance {
	
	public static String fieldName = "appearance";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String type ) {
		Field field = new Field( fieldName, type, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
