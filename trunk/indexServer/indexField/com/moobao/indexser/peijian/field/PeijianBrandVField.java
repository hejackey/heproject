package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件品牌(eg.诺基亚)
 * @author liuxueyong
 */
public class PeijianBrandVField {
	
	public static String fieldName = "peijianBrandV";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String peijianBrandV ) {
		Field field = new Field( fieldName, peijianBrandV, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field;
	}
}
