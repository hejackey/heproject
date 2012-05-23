package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 手机的一般搜索Field
 * 由名称(productname),简单描述(describe)和推荐语(promotion)组成.
 * @author liuxueyong
 */
public class PhoneContentField {
	
	public static String fieldName = "phoneSearchField";
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
