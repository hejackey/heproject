package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取显示图片.
 * @author liuxueyong
 */
public class ShowPicField {
	
	public static String fieldName = "showPic";
	/**
	 * @param showPic
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String showPic ) {
		Field field = new Field( fieldName, showPic, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
