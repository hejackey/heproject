package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件分类(eg.103)
 * @author liuxueyong
 */
public class PeiJianProductClassField {
	
	public static String fieldName = "productClass";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String productClass ) {
		Field field = new Field( fieldName, productClass, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
