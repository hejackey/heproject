package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件分类代码
 * @author liuxueyong
 */
public class PeijianClassCodeField {
	
	public static String fieldName = "peijianClassCode";
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
