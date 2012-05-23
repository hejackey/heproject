
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心"小图名"
 * @author liuxueyong
 */
public class EntMinPicField {
	
	public static String fieldName = "entMinPic";
	/**
	 * @param entMinPic
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entMinPic ){
		Field field = new Field( fieldName, entMinPic, Field.Store.YES, Field.Index.NO );
		return field;
	}	
}
