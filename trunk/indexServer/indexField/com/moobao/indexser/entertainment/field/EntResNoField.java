package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心资源编号
 * @author liuxueyong
 */
public class EntResNoField {

	public static String fieldName = "entResNo";
	/**
	 * @param entResNo
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entResNo ){
		Field field = new Field( fieldName, entResNo, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}	
}
