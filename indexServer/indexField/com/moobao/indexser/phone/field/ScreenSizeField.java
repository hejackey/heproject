package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机屏幕尺寸
 * @author liuxueyong
 */
public class ScreenSizeField {
	
	public static String fieldName = "screenSize";
	/**
	 * @param size
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String size ) {
		Field field = new Field( fieldName, size, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
