
package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机图片列表
 * @author liuxueyong
 */
public class PicListField {
	
	public static String fieldName = "picList";
	/**
	 * @param photoName
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String pic ){
		Field field = new Field( fieldName, pic, Field.Store.YES, Field.Index.NO );
		return field ;
	}	
}
