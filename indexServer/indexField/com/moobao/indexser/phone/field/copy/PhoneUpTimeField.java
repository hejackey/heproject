
package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机的上架时间.
 * @author liuxueyong
 */
public class PhoneUpTimeField {
	
	public static String fieldName = "phoneUpTime";
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
