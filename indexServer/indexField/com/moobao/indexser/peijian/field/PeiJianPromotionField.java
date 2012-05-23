package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件的推荐语
 * @author liuxueyong
 */
public class PeiJianPromotionField {
	
	public static String fieldName = "peiJianPromotion";
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
