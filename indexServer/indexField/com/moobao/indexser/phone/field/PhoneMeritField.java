package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机优点
 * @author liuxueyong
 */
public class PhoneMeritField {
	
	public static String fieldName = "phoneMerit";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String merit ) {
		Field field = new Field( fieldName, merit, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
