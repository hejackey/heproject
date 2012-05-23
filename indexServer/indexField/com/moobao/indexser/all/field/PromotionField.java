package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机的推荐语
 * @author liuxueyong
 */
public class PromotionField {
	
	public static String fieldName = "promotion";
	/**
	 * @param promotion
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String promotion ) {
		Field field = new Field( fieldName, promotion, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
