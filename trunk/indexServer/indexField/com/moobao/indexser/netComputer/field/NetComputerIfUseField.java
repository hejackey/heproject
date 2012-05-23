package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 是否上架（0代表未上架，1代表上架,2 预告 ,3预售 ,4 缺货）
 * @author liuxueyong
 */
public class NetComputerIfUseField {
	
	public static String fieldName = "ifuse";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String type ) {
		Field field = new Field( fieldName, type, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
