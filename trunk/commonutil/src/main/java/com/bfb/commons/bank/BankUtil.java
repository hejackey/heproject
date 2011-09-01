package com.bfb.commons.bank;

import java.util.HashMap;
import java.util.Map;

public class BankUtil {
	private static Map<String,String> bankName=new HashMap<String,String>();
	private static Map<String,String> bankCode=new HashMap<String,String>();
	
	static{
		bankName.put("中国工商银行", "ICBC");
		bankName.put("招商银行", "CMB");
		bankName.put("中国建设银行", "CCB");
		bankName.put("中国农业银行", "ABC");
		bankName.put("中国民生银行", "CMBC");
		bankName.put("光大银行", "CEB");
		bankName.put("广东发展银行", "GDB");
		bankName.put("交通银行", "BOCO");
		bankName.put("上海浦东发展银行", "SPDB");
		bankName.put("兴业银行", "CIB");
		bankName.put("中国银行", "BOC");
		bankName.put("深圳发展银行", "SDB");
		bankName.put("北京银行", "BCCB");
		bankName.put("中信银行", "ECITIC");
		bankName.put("中国邮政储蓄银行", "POST");
		
		bankCode.put("ICBC","中国工商银行");
		bankCode.put("CMB","招商银行" );
		bankCode.put("CCB","中国建设银行" );
		bankCode.put("ABC","中国农业银行" );
		bankCode.put("CMBC","中国民生银行" );
		bankCode.put("CEB","光大银行" );
		bankCode.put("GDB","广东发展银行" );
		bankCode.put("BOCO","交通银行" );
		bankCode.put("SPDB","上海浦东发展银行" );
		bankCode.put("CIB","兴业银行" );
		bankCode.put("BOC","中国银行" );
		bankCode.put("SDB","深圳发展银行" );
		bankCode.put("BCCB","北京银行" );
		bankCode.put("ECITIC","中信银行" );
		bankCode.put("POST","中国邮政储蓄银行" );
	}
	
	public static String getBankCodeByName(String bName){
		return bankName.get(bName);
	}
	
	public static String getBankNameByCode(String bCode){
		return bankCode.get(bCode);
	}
}
