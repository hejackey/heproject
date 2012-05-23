package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本分类代码
 * @author liuxueyong
 */
public class NetComputerClassCodeField {
	
	public static String fieldName = "netComputerClassCode";
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
