package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 主商品表的售销状态（0代表下架，1代表上架,2 预告 ,3预售 ,4 缺货）
 *  有一个在售的置为 在售
    所有的商品缺货为 缺货
    所有的商品为预售 预售
    所有的商品为预告 预告
    所有的商品下架为 下架
 * @author liuxueyong
 */
public class IfUseField {
	
	public static String fieldName = "ifuse";
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
