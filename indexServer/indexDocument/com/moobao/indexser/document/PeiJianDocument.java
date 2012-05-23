package com.moobao.indexser.document;

import java.text.DecimalFormat;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.moobao.indexser.peijian.field.PeiJianContentField;
import com.moobao.indexser.peijian.field.PeiJianDescribeField;
import com.moobao.indexser.peijian.field.PeiJianIdField;
import com.moobao.indexser.peijian.field.PeiJianIfUseField;
import com.moobao.indexser.peijian.field.PeiJianKeywordsField;
import com.moobao.indexser.peijian.field.PeiJianMarketTimeField;
import com.moobao.indexser.peijian.field.PeiJianNameField;
import com.moobao.indexser.peijian.field.PeiJianNetPriceRangField;
import com.moobao.indexser.peijian.field.PeiJianParentIdField;
import com.moobao.indexser.peijian.field.PeiJianPicListField;
import com.moobao.indexser.peijian.field.PeiJianPriceMarketField;
import com.moobao.indexser.peijian.field.PeiJianProductClassField;
import com.moobao.indexser.peijian.field.PeiJianPromotionField;
import com.moobao.indexser.peijian.field.PeiJianSaleCountField;
import com.moobao.indexser.peijian.field.PeijianAskCountField;
import com.moobao.indexser.peijian.field.PeijianBaseParaField;
import com.moobao.indexser.peijian.field.PeijianBrandVField;
import com.moobao.indexser.peijian.field.PeijianClassCodeField;
import com.moobao.indexser.peijian.field.PeijianNetHighPriceSortField;
import com.moobao.indexser.peijian.field.PeijianNetLowPriceSortField;
import com.moobao.indexser.peijian.field.PeijianPrimaryId;
import com.moobao.indexser.peijian.field.PeijianRebateField;
import com.moobao.indexser.peijian.field.PeijianRecommendCountField;
import com.moobao.indexser.peijian.field.PeijianRelaBrandField;
import com.moobao.indexser.peijian.field.PeijianRelaModelField;

/**
 * 根据参数，取出所有的配件Field的信息，加入document.
 * @author liuxueyong
 */
public class PeiJianDocument {
	
	
	
	public static Document getDocument(String pri_productid, String classcode, String productclass, String brand_name, String product_para,
			String productname, String describe, String promotion, int saleCount, String market_time, String rebate, 
			String keywords, String productid, double marketPrice, double priceNetMin, double priceNetMax, 
			int ifuse, int recommendCount, int askCount, String picList, String parentid, int brand, String brandModel) {

		//定义一个Document
		Document doc = new Document();
		//名称+详细描述+推荐语
		StringBuffer searchContent = new StringBuffer();
		//摩宝价格(整数部分统一为12位, 不足补0)，范围搜索
		String netPriceRang = "";
		//要保存入Field的日期格式.
		String upDate = "", downDate = "";

		searchContent.append( productname + " " );
		searchContent.append( describe + " ");
		searchContent.append( promotion + " " );
		searchContent.append( product_para );

		//将咪啦价最小价与最高价转化为字符串型,可排序.
		String priceMin = priceNetMin + "";
		String priceMax = priceNetMax + "";


		//将整数部分扩为统一的12位
		DecimalFormat df = new DecimalFormat( "000000000000.000" );
		netPriceRang = df.format( priceNetMin );


		//将日期"上架时间"和"下架时间"转为字符串
//		SimpleDateFormat sf = new SimpleDateFormat( "yyyyMMdd" );
//		if( upTime != null )
//			upDate = sf.format( upTime );
//		if( downTime != null)
//			downDate = sf.format( downTime );
		
		StringBuffer marketTimeFormat = new StringBuffer();
		if( !market_time.equals("") && !market_time.equals(null) && market_time.contains("-") ) {
			int first = market_time.indexOf("-");
			int last = market_time.lastIndexOf("-");
			String one = market_time.substring( first + 1, last ).trim();
			String two = market_time.substring( last + 1 ).trim();
			if( one.length() < 2 )
				one = "0" + one ;
			if( two.length() < 2 )
				two = "0" + two;
			marketTimeFormat.append( market_time.substring(0, first) );
			marketTimeFormat.append( one );
			marketTimeFormat.append( two );
		}

		Field primaryId = PeijianPrimaryId.getField(pri_productid);
		Field classCode = PeijianClassCodeField.getField(classcode);
		Field prodClass = PeiJianProductClassField.getField(productclass);
		Field brandName = PeijianBrandVField.getField( brand_name );
		Field basePara = PeijianBaseParaField.getField( product_para );
		
		Field name = PeiJianNameField.getField( productname );
		Field describeP = PeiJianDescribeField.getField(describe);
		Field promotions = PeiJianPromotionField.getField( promotion );
		Field saleCountt = PeiJianSaleCountField.getField(saleCount+"");
		
		Field marketDate = PeiJianMarketTimeField.getField( marketTimeFormat.toString() );
		Field prebate = PeijianRebateField.getField(rebate);
		Field key = PeiJianKeywordsField.getField( keywords );
		Field id  = PeiJianIdField.getField(productid);
		
		Field market = PeiJianPriceMarketField.getField( marketPrice+"" );
		Field sortPrice = PeijianNetLowPriceSortField.getField( priceMin );
		Field maxPrice = PeijianNetHighPriceSortField.getField( priceMax );
		Field rangPrice = PeiJianNetPriceRangField.getField( netPriceRang );
		
		Field IfUse = PeiJianIfUseField.getField(ifuse+"");
//		Field uptime = PhoneUpTimeField.getField( upDate );
//		Field downtime = PhoneDownTimeField.getField( downDate );
		Field pics = PeiJianPicListField.getField( picList );
		Field recommendcount = PeijianRecommendCountField.getField(recommendCount+"");
		Field askcount = PeijianAskCountField.getField(askCount+"");
		
		Field parentId = PeiJianParentIdField.getField(parentid);
		Field brandV = PeijianRelaBrandField.getField(brand+"");                //配件相关手机品牌
		Field brandM = PeijianRelaModelField.getField(brandModel);                  //配件相关手机型号
		Field content = PeiJianContentField.getField( searchContent.toString() );
		

		doc.add( primaryId );
		doc.add( classCode );
		doc.add( prodClass );
		doc.add( brandName );
		doc.add( basePara );
		
		doc.add( name );
		doc.add( describeP );
		doc.add( promotions );
		doc.add( saleCountt );
		
		doc.add( marketDate );
		doc.add( prebate );
		doc.add( key );
		doc.add( id );
		
		doc.add( market );
		doc.add( sortPrice );
		doc.add( maxPrice );
		doc.add( rangPrice );
		
		doc.add( IfUse );
		doc.add( pics );
		doc.add( recommendcount );
		doc.add( askcount );
		
		doc.add( parentId );
		doc.add( brandV );
		doc.add( brandM );
		doc.add( content );

		return doc;
	}
}
