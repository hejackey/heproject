package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机权重
 * @author liuxueyong
 */
public class PhoneWeightIndexField {
	
	public static String fieldName = "weightindex";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String weight ) {
		Field field = new Field( fieldName, weight, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
