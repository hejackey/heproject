package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 上网本的一般搜索Field
 * 由名称(productname),详细描述(memo)和推荐语(promotion)组成.
 * @author liuxueyong
 */
public class NetComputerContentField {
	
	public static String fieldName = "NetComputerSearchField";
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
