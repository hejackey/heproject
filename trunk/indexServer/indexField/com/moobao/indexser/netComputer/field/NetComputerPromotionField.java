package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本的推荐语
 * @author liuxueyong
 */
public class NetComputerPromotionField {
	
	public static String fieldName = "netComputerPromotion";
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
