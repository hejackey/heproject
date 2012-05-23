package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 配件的一般搜索Field
 * 由名称(productname),详细描述(memo)和推荐语(promotion)组成.
 * @author liuxueyong
 */
public class PeiJianContentField {
	
	public static String fieldName = "PeiJianSearchField";
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
