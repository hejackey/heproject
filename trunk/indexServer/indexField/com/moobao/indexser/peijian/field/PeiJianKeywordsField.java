package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件的关键词.
 * @author liuxueyong
 */
public class PeiJianKeywordsField {
	
	public static String fieldName = "peiJianKeywords";
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
