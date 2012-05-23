package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机是否编辑推荐
 * @author liuxueyong
 */
public class PhoneIfRecommendField {
	
	public static String fieldName = "ifRecommend";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String ifRecommend ) {
		Field field = new Field( fieldName, ifRecommend, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
