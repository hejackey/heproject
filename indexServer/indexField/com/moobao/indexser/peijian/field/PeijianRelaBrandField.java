package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件关联的手机品牌(eg.诺基亚:10002)
 * @author liuxueyong
 */
public class PeijianRelaBrandField {
	
	public static String fieldName = "peijianRelaBrand";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String peijianRelaBrand ) {
		Field field = new Field( fieldName, peijianRelaBrand, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
