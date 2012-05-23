package com.moobao.indexser.document;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.moobao.indexser.resource.ArticleAbstractField;
import com.moobao.indexser.resource.ArticleContentField;
import com.moobao.indexser.resource.ArticleCreateDTField;
import com.moobao.indexser.resource.ArticleIDField;
import com.moobao.indexser.resource.ArticlePubTimeField;
import com.moobao.indexser.resource.ArticleSourceNetField;
import com.moobao.indexser.resource.ArticleSourceUrlField;
import com.moobao.indexser.resource.ArticleTitleField;
import com.moobao.indexser.resource.ArticleTypeIDField;
import com.moobao.indexser.resource.PicUrlField;
import com.moobao.indexser.resource.ShowPicField;

/**
 * 根据参数，取出所有的资讯Field的信息，加入document.
 * 
 * @author liuxueyong
 */
public class ResourceDocument {
	public static Document getDocument(String articleID, String articleTypeID,
			String title, Date createDT, String keywords, String tags, String sourceUrl,
			String sourceNet, String zhaiYao, Date publishTime, String picUrl,
			String showPic) {

		// 定义一个Document
		Document doc = new Document();
		
		//组合搜索content(关建字、摘要、标签)
		StringBuffer content = new StringBuffer();
		content.append(title + " ");
		content.append(keywords + " ");
		content.append(zhaiYao + " ");
		content.append(tags);

		// 将"添加日期"和"发表日期"转为字符串
		String date_add = "", date_pub = "" ;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		if( createDT != null )
			date_add = sf.format(createDT);
		if( publishTime != null )
			date_pub = sf.format(publishTime); 

		Field id = ArticleIDField.getField(articleID);
		Field typeID = ArticleTypeIDField.getField(articleTypeID);
		Field articleTitle = ArticleTitleField.getField(title);
		Field createDate = ArticleCreateDTField.getField(date_add);
		
		
		
		Field contentSearch = ArticleContentField.getField(content.toString());
		
		
		Field sourceSite = ArticleSourceUrlField.getField(sourceUrl);
		Field SourceName = ArticleSourceNetField.getField(sourceNet);
		Field zhaiyao = ArticleAbstractField.getField(zhaiYao);

		Field publishTtime = ArticlePubTimeField.getField(date_pub);
		Field pic = PicUrlField.getField(picUrl);
		Field showPicture = ShowPicField.getField(showPic);

		doc.add(id);
		doc.add(typeID);
		doc.add(articleTitle);
		doc.add(createDate);

		doc.add(contentSearch);
		doc.add(sourceSite);
		doc.add(SourceName);
		doc.add(zhaiyao);

		doc.add(publishTtime);
		doc.add(pic);
		doc.add(showPicture);

		return doc;
	}
}
