package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机分类代码
 * @author liuxueyong
 */
public class ClassCodeField {
	
	public static String fieldName = "classCode";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String classcode ) {
		Field field = new Field( fieldName, classcode, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
