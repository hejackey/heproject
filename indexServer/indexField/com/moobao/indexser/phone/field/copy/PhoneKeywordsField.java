package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机的关键词.
 * @author liuxueyong
 */
public class PhoneKeywordsField {
	
	public static String fieldName = "phoneKeywords";
	public static float weight = 2.0f;
	/**
	 * @param String Keywords
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String Keywords ){
		Field field = new Field( fieldName, Keywords, Field.Store.YES, Field.Index.TOKENIZED );
		field.setBoost(weight);
		return field ;
	}
}
