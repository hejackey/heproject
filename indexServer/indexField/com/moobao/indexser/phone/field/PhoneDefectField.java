package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机缺点(eg.诺基亚)
 * @author liuxueyong
 */
public class PhoneDefectField {
	
	public static String fieldName = "phoneDefect";
	/**
	 * @param brand
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String defect ) {
		Field field = new Field( fieldName, defect, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
