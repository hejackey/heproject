package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本的上市时间.
 * @author liuxueyong
 */
public class NetComputerMarketTimeField {

	public static String fieldName = "netComputerMarketTime";
	/**
	 * @param String marketTime
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String marketTime ){
		Field field = new Field( fieldName, marketTime, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}
}
