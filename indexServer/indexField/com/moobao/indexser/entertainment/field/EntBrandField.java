
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心手机型号的汉字品牌
 * @author liuxueyong
 */
public class EntBrandField {
	
	public static String fieldName = "entBrand";
	/**
	 * @param entBrand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entBrand ){
		Field field = new Field( fieldName, entBrand, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}	
}
