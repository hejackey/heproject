
package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机名称
 * @author liuxueyong
 */
public class PhoneNameField {
	
	public static String fieldName = "phoneName";
	public static float weight = 1.5f;
	
	/**
	 * @param pname
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String pname ){
		Field field = new Field( fieldName, pname, Field.Store.YES, Field.Index.TOKENIZED );
		field.setBoost(weight);
		return field;
	}
}
