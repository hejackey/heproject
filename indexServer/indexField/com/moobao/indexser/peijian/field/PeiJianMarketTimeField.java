package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件的上市时间.
 * @author liuxueyong
 */
public class PeiJianMarketTimeField {

	public static String fieldName = "peiJianMarketTime";
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
