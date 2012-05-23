
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心简介
 * @author liuxueyong
 */
public class EntDescribeField {
	
	public static String fieldName = "entDescribe";
	/**
	 * @param entDescribe
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entDescribe ){
		Field field = new Field( fieldName, entDescribe, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}	
}
