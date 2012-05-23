package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机摄像头像素
 * @author liuxueyong
 */
public class CameraPixelField {
	
	public static String fieldName = "CameraPixel";
	/**
	 * @param pixel
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String pixel ) {
		Field field = new Field( fieldName, pixel, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
