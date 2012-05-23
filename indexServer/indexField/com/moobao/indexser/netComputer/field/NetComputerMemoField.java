package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 上网本详细描述
 * @author liuxueyong
 */
public class NetComputerMemoField {
	
	public static String fieldName = "netComputerMemo";
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
