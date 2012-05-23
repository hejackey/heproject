package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件主商品ID(主商品表)
 * @author liuxueyong
 */
public class PeijianPrimaryId {
	
	public static String fieldName = "peijianPrimaryId";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String peijianPrimaryId ) {
		Field field = new Field( fieldName, peijianPrimaryId, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
