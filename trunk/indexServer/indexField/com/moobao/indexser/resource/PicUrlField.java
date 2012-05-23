package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取图片地址.
 * @author liuxueyong
 */
public class PicUrlField {
	
	public static String fieldName = "picUrl";
	/**
	 * @param picUrl
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String picUrl ) {
		Field field = new Field( fieldName, picUrl, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
