package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机适合人群
 * @author liuxueyong
 */
public class PhoneCrowdNameField {
	
	public static String fieldName = "crowdName";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String crowdName ) {
		Field field = new Field( fieldName, crowdName, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
