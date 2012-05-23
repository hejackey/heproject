package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 手机的一般搜索Field
 * 由名称(productname),关键词,基本参数(PRODUCT_PARA)和推荐语(promotion)组成.
 * @author liuxueyong
 */
public class ContentField {
	
	public static String fieldName = "SearchField";
	/**
	 * @param searchPhrase
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String searchPhrase ){
		Field field = new Field( fieldName, searchPhrase, Field.Store.YES, Field.Index.TOKENIZED );
		return field ;
	}	
}
