package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本的下架时间.
 * @author liuxueyong
 */
public class NetComputerDownTimeField {
	
	public static String fieldName = "netComputerDownTime";
	/**
	 * 手机的下架时间
	 * @param String
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String DownTime ) {
		Field field = new Field( fieldName, DownTime, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
