
package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机的上架时间.
 * @author liuxueyong
 */
public class UpTimeField {
	
	public static String fieldName = "upTime";
	/**
	 * 手机的上架时间
	 * @param String
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String upTime ){
		Field field = new Field( fieldName, upTime, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
