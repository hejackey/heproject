package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本主商品ID(主商品表)
 * @author liuxueyong
 */
public class NetComputerPrimaryId {
	
	public static String fieldName = "netComputerPrimaryId";
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
