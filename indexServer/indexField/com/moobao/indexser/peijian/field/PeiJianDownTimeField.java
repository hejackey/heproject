package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件的下架时间.
 * @author liuxueyong
 */
public class PeiJianDownTimeField {
	
	public static String fieldName = "peiJianDownTime";
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
