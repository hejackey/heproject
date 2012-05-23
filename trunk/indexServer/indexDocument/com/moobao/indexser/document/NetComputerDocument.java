package com.moobao.indexser.document;

import java.text.DecimalFormat;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.moobao.indexser.document.number.NumberJudge;
import com.moobao.indexser.netComputer.field.NetComputerAppearance;
import com.moobao.indexser.netComputer.field.NetComputerAskCountField;
import com.moobao.indexser.netComputer.field.NetComputerBaseParaField;
import com.moobao.indexser.netComputer.field.NetComputerBrandField;
import com.moobao.indexser.netComputer.field.NetComputerBrandVField;
import com.moobao.indexser.netComputer.field.NetComputerClassCodeField;
import com.moobao.indexser.netComputer.field.NetComputerColorField;
import com.moobao.indexser.netComputer.field.NetComputerContentField;
import com.moobao.indexser.netComputer.field.NetComputerDescribeField;
import com.moobao.indexser.netComputer.field.NetComputerIdField;
import com.moobao.indexser.netComputer.field.NetComputerIfUseField;
import com.moobao.indexser.netComputer.field.NetComputerKeywordsField;
import com.moobao.indexser.netComputer.field.NetComputerMarketTimeField;
import com.moobao.indexser.netComputer.field.NetComputerModelField;
import com.moobao.indexser.netComputer.field.NetComputerNameField;
import com.moobao.indexser.netComputer.field.NetComputerNetHighPriceSortField;
import com.moobao.indexser.netComputer.field.NetComputerNetLowPriceSortField;
import com.moobao.indexser.netComputer.field.NetComputerNetPriceRangField;
import com.moobao.indexser.netComputer.field.NetComputerPicListField;
import com.moobao.indexser.netComputer.field.NetComputerPriceMarketField;
import com.moobao.indexser.netComputer.field.NetComputerPrimaryId;
import com.moobao.indexser.netComputer.field.NetComputerProductClassField;
import com.moobao.indexser.netComputer.field.NetComputerPromotionField;
import com.moobao.indexser.netComputer.field.NetComputerRebateField;
import com.moobao.indexser.netComputer.field.NetComputerRecommendCountField;
import com.moobao.indexser.netComputer.field.NetComputerSaleCountField;

/**
 * 根据参数，取出所有的上网本Field的信息，加入document.
 * @author liuxueyong
 */
public class NetComputerDocument {
	
	
	
	public static Document getDocument(String pri_productid,String classcode, int brandv, String brand_name, String productModel, String product_para,
			String productname, String describe, String promotion, int saleCount, String market_time, String rebate, String keywords, String net_name, 
			int camera_pixel, double screen_size, String productid, double price_market, double priceNetMin, double priceNetMax, 
			String color, int ifuse, int recommendCount, int askCount, String picList, String shape, int productclass) {

		//定义一个Document
		Document doc = new Document();
		//名称+描述+推荐语
		StringBuffer searchContent = new StringBuffer();
		//咪啦价格(整数部分统一为12位, 不足补0)，范围搜索
		String netPriceRang = "";
		//要保存入Field的日期格式.
//		String upDate = "", downDate = "";
		
		searchContent.append( productname + " " );
		searchContent.append( keywords + " " );
		searchContent.append( promotion + " ");
		searchContent.append( product_para + " ");
		String modeList = "";
		if( productModel != null && !productModel.trim().equals("") ) {
			modeList = NumberJudge.splitProductModel( productModel );
		}
		searchContent.append( modeList );
		
		//将咪啦价priceNetMin和priceNetMax转化为字符串型,可排序.
		String netMin = priceNetMin + "";    //同型号的最低价
		String netMax = priceNetMax + "";    //同型号的最高价
		
		
		//将"咪啦价"整数部分扩为统一的12位(高级搜索)
		DecimalFormat df = new DecimalFormat( "000000000000.000" );
		netPriceRang = df.format( priceNetMin );
		
		//将"摄像头像素"扩为统一的8位(高级搜索)
		df = new DecimalFormat( "00000000" );
		String cameraRang = df.format( (double)camera_pixel );
		
		//将日期"上架时间"和"下架时间"转为字符串
//		SimpleDateFormat sf = new SimpleDateFormat( "yyyyMMdd" );
//		if( upTime != null )
//			upDate = sf.format( upTime );
//		if( downTime != null )
//			downDate = sf.format( downTime );
		
		//将productModel的各种形式加入到keywords中.
		StringBuffer keybuff = new StringBuffer();
		keybuff.append( keywords );
		if( productModel != null && !productModel.trim().equals("") ) {
			keybuff.append( modeList );
		}
		
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
			
		Field primaryId = NetComputerPrimaryId.getField(pri_productid);
		Field classCode = NetComputerClassCodeField.getField(classcode);
		Field brandVF = NetComputerBrandVField.getField(brandv+"");
		Field brand = NetComputerBrandField.getField( brand_name );
		
		Field pModel = NetComputerModelField.getField( productModel );
		Field basePara = NetComputerBaseParaField.getField( product_para );
		Field name = NetComputerNameField.getField( productname );
		Field describeP = NetComputerDescribeField.getField(describe);
		
		Field promotions = NetComputerPromotionField.getField( promotion );
		Field saleCountt = NetComputerSaleCountField.getField(saleCount+"");
		Field marketDate = NetComputerMarketTimeField.getField( marketTimeFormat.toString() );
		Field prebate = NetComputerRebateField.getField(rebate);
		
		Field key = NetComputerKeywordsField.getField( keybuff.toString() );
//		Field netType = NetWorkTypeField.getField( net_name );
//		Field camera = CameraPixelField.getField( cameraRang + "" );
//		Field screen = ScreenSizeField.getField( screen_size + "" );
		
		Field id  = NetComputerIdField.getField(productid);
		Field market = NetComputerPriceMarketField.getField( price_market+"" );
		Field sortPrice = NetComputerNetLowPriceSortField.getField( netMin );
		Field maxPrice = NetComputerNetHighPriceSortField.getField( netMax );
		
		Field rangPrice = NetComputerNetPriceRangField.getField( netPriceRang );
		Field pcolor = NetComputerColorField.getField(color);         //有货颜色的组合字串
		Field IfUse = NetComputerIfUseField.getField(ifuse+"");
//		Field uptime = PhoneUpTimeField.getField( upDate );
//		Field downtime = PhoneDownTimeField.getField( downDate );
		Field pics = NetComputerPicListField.getField( picList );
		

		Field recommendcount = NetComputerRecommendCountField.getField(recommendCount+"");
		Field askcount = NetComputerAskCountField.getField(askCount+"");
		Field content = NetComputerContentField.getField( searchContent.toString() );
		Field style = NetComputerAppearance.getField(shape);       //外观
		
		Field productClass = NetComputerProductClassField.getField(productclass+"");
	
		doc.add( primaryId );
		doc.add( classCode );
		doc.add( brandVF );
		doc.add( brand );
		
		doc.add( pModel );
		doc.add( basePara );
		doc.add( name );
		doc.add( describeP );
		
		doc.add( promotions );
		doc.add( saleCountt );
		doc.add( marketDate );
		doc.add( prebate );
		
		doc.add( key );
//		doc.add( netType );
//		doc.add( camera );
//		doc.add( screen );
		
		doc.add( id );
		doc.add( market );
		doc.add( sortPrice );
		doc.add( maxPrice );
		
		doc.add( rangPrice );
		doc.add( pcolor );
		doc.add( IfUse );
		doc.add( pics );
		
		doc.add(recommendcount);
		doc.add(askcount);
		doc.add(content);
		doc.add(style);
		
		doc.add(productClass);
		return doc;
	}
}
