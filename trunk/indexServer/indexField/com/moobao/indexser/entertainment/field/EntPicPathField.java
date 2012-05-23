
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心"图片路径"
 * @author liuxueyong
 */
public class EntPicPathField {
	
	public static String fieldName = "entPicPath";
	/**
	 * @param entPicPath
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entPicPath ){
		Field field = new Field( fieldName, entPicPath, Field.Store.YES, Field.Index.NO );
		return field;
	}	
}
