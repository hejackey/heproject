package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机颜色
 * @author liuxueyong
 */
public class ColorField {
	
	public static String fieldName = "color";
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
