package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件关联手机型号(例:n73)
 * @author liuxueyong
 */
public class PeijianRelaModelField {
	public static String fieldName = "peijianRelaModel";
	/**
	 * @param model
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String peijianRelaModel ) {
		Field field = new Field( fieldName, peijianRelaModel, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
