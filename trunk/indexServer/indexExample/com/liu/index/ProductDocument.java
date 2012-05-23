package com.liu.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
public class ProductDocument {

	private static final String PRODUCT_ID = "productid";

	private static final String INDEX_TIME = "indextime";

	private static final String PRODUCT_URL = "producturl";

	private static final String CATEGORY = "category";

	private static final String PRODUCT_NAME = "name";

	private static final String PRODUCT_TYPE = "type";

	public static Document buildProductDocument() {

		Document doc = new Document();

		Field identifier = new Field(PRODUCT_ID, 12 + "", Field.Store.YES,
				Field.Index.NO);

		long mills = System.currentTimeMillis();
		Field indextime = new Field(INDEX_TIME, mills + "", Field.Store.YES,
				Field.Index.UN_TOKENIZED);

		Field producturl = new Field(PRODUCT_URL, "",
				Field.Store.YES, Field.Index.UN_TOKENIZED);

		Field name = new Field(PRODUCT_NAME, "主要功能: N95内置天线,手机时钟,内置震动,情景模式,通话时间提示,免提通话,待机图片,动画屏保功能,来电图片识别,来电铃声识别,飞行模式 附加功能: 闹钟,关机闹钟,日历,计算器,日程表,记事本,备忘录,语音备忘录,世界时钟,货币换算,单位换算,定时器,秒表",
				Field.Store.YES, Field.Index.TOKENIZED);

		Field type = new Field(PRODUCT_TYPE, "N73",
				Field.Store.YES, Field.Index.TOKENIZED);
		
		String text = "诺基亚";
		text += " " + "N73";
		Field all = new Field("all", text, Field.Store.YES, Field.Index.TOKENIZED);

		// add all
		doc.add(identifier);
		doc.add(indextime);
		doc.add(producturl);
		doc.add(name);
		doc.add(type);
		doc.add(all);
		System.out.println( doc );

		return doc;

	}

}
