
package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机图片列表
 * @author liuxueyong
 */
public class PhonePicListField {
	
	public static String fieldName = "phonePicList";
	/**
	 * @param photoName
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String photoName ){
		Field field = new Field( fieldName, photoName, Field.Store.YES, Field.Index.NO );
		return field ;
	}	
}
