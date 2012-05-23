
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心 "资源路径"
 * @author liuxueyong
 */
public class EntResPathField {
	
	public static String fieldName = "entResPath";
	/**
	 * @param entResPath
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entResPath ){
		Field field = new Field( fieldName, entResPath, Field.Store.YES, Field.Index.NO );
		return field;
	}	
}
