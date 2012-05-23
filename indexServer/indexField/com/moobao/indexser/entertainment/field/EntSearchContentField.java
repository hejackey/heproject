
package com.moobao.indexser.entertainment.field;

import org.apache.lucene.document.Field;

/**
 * 获取娱乐中心"搜索字段(资源名称、简介)"
 * @author liuxueyong
 */
public class EntSearchContentField {
	
	public static String fieldName = "entSearchContent";
	/**
	 * @param entSearchContent
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String entSearchContent ){
		Field field = new Field( fieldName, entSearchContent, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}	
}
