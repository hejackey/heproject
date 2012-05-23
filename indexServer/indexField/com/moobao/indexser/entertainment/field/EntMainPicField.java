
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 "主图名"
 * @author liuxueyong
 */
public class EntMainPicField {
	
	public static String fieldName = "entMainPic";
	/**
	 * @param entMainPic
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entMainPic ){
		Field field = new Field( fieldName, entMainPic, Field.Store.YES, Field.Index.NO );
		return field;
	}	
}
