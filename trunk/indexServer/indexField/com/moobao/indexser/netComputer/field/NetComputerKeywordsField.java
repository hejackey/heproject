package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本的关键词.
 * @author liuxueyong
 */
public class NetComputerKeywordsField {
	
	public static String fieldName = "netComputerKeywords";
	public static float weight = 2.0f;
	/**
	 * @param String Keywords
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String Keywords ){
		Field field = new Field( fieldName, Keywords, Field.Store.YES, Field.Index.TOKENIZED );
		field.setBoost(weight);
		return field ;
	}
}
