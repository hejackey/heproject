package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 配件详细描述
 * @author liuxueyong
 */
public class PeiJianMemoField {
	
	public static String fieldName = "peiJianMemo";
	/**
	 * @param memo
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String memo ) {
		Field field = new Field( fieldName, memo, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
