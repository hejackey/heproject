package com.moobao.service.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

import com.apply.b2b.cms.util.StringUtil;

public class UrlParameterEncoder {
	public static String getUrlParameterEncodeStr(String para) {
		if (StringUtils.isNotEmpty("para")) {
			try {
				String encodePara = URLEncoder.encode(para, "utf-8");
				encodePara = StringUtil.Replace(encodePara, "%2F", "%252F");
				return encodePara;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}

	public static String getUrlParameterDecodeStr(String para) {
		if (StringUtils.isNotEmpty("para")) {
			try {
				para = StringUtil.Replace(para, "%252F", "%2F");
				String decodePara = URLDecoder.decode(para, "utf-8");
				return decodePara;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}
	
	public static String getFunc(int value){
		String result = "";
		if(((int)(Math.pow(2, 13))& value)>=1){
			result += ",全键盘";
		}
		if(((int)(Math.pow(2, 12))& value)>=1){
			result += ",双摄像头";
		}
		if(((int)(Math.pow(2, 11))& value)>=1){
			result += ",GPS";
		}
		if(((int)(Math.pow(2, 10))& value)>=1){
			result += ",A-GPS";
		}
		if(((int)(Math.pow(2, 9))& value)>=1){
			result += ",MP3";
		}
		if(((int)(Math.pow(2, 8))& value)>=1){
			result += ",MP4";
		}
		if(((int)(Math.pow(2, 7))& value)>=1){
			result += ",手写";
		}
		if(((int)(Math.pow(2, 6))& value)>=1){
			result += ",触屏";
		}
		if(((int)(Math.pow(2, 5))& value)>=1){
			result += ",拍照";
		}
		if(((int)(Math.pow(2, 4))& value)>=1){
			result += ",Wi-Fi";
		}
		if(((int)(Math.pow(2, 3))& value)>=1){
			result += ",音乐";
		}
		if(((int)(Math.pow(2, 2))& value)>=1){
			result += ",蓝牙";
		}
		if(((int)(Math.pow(2, 1))& value)>=1){
			result += ",收音机";
		}
		if(((int)(Math.pow(2, 0))& value)>=1){
			result += ",视频播放";
		}
		return result;
	}
	
	//外形
	public static String getShape(int shape){
		String shape_name = "";
		switch(shape) {
		case 101001:
			shape_name = "直板";
			break;
		case 101002:
			shape_name = "翻盖";
			break;
		case 101003:
			shape_name = "滑盖";
			break;
		case 101004:
			shape_name = "旋转";
			break;
		case 101005:
			shape_name = "旋屏";
			break;
		default:
			shape_name = "";
		}
		return shape_name;
	}
	
	//网络制式
	public static String getNet(int net){
		String net_name = "";
		if( ((int)(Math.pow(2, 6)) & net ) >= 1 ) {
			net_name = "GSM";
		}
		if( ((int)(Math.pow(2, 5)) & net ) >= 1 ) {
			net_name = "CDMA";
		}
		if( ((int)(Math.pow(2, 4)) & net ) >= 1 ) {
			net_name = "双卡双待";
		}
		if( ((int)(Math.pow(2, 3)) & net ) >= 1 ) {
			net_name = "双模双待";
		}
		if( ((int)(Math.pow(2, 2)) & net ) >= 1 ) {
			net_name = "TD_CDMA";
		}
		if( ((int)(Math.pow(2, 1)) & net ) >= 1 ) {
			net_name = "WCDMA";
		}
		if( ((int)(Math.pow(2, 0)) & net ) >= 1 ) {
			net_name = "CDMA2000";
		}
		return net_name;
	}
	
	public static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,        
        0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,        
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };    
   
   
public static String unescape(String s) {        
        StringBuffer sbuf = new StringBuffer();        
        int i = 0;        
        int len = s.length();        
        while (i < len) {        
        int ch = s.charAt(i);        
        if ('A' <= ch && ch <= 'Z') {    
        sbuf.append((char) ch);        
        } else if ('a' <= ch && ch <= 'z') {         
        sbuf.append((char) ch);        
        } else if ('0' <= ch && ch <= '9') {     
            sbuf.append((char) ch);        
        } else if (ch == '-' || ch == '_'|| ch == '.' || ch == '!' || ch == '~' || ch == '*'|| ch == '\'' || ch == '(' || ch == ')') {        
        sbuf.append((char) ch);        
        } else if (ch == '%') {    
            int cint = 0;        
            if ('u' != s.charAt(i + 1)) {        
            cint = (cint << 4) | val[s.charAt(i + 1)];        
            cint = (cint << 4) | val[s.charAt(i + 2)];        
            i += 2;        
            } else {        
                cint = (cint << 4) | val[s.charAt(i + 2)];        
                cint = (cint << 4) | val[s.charAt(i + 3)];        
                cint = (cint << 4) | val[s.charAt(i + 4)];        
                cint = (cint << 4) | val[s.charAt(i + 5)];        
                i += 5;        
            }        
            sbuf.append((char) cint);    
        } else {        
            sbuf.append((char) ch);        
        }        
        i++;        
        }        
        return sbuf.toString();        
    } 

	public static void main(String[] ss) {
		System.out.println(getUrlParameterEncodeStr("三星\\"));
		String v = UrlParameterEncoder
				.getUrlParameterEncodeStr("d dd   +_)(^%$#@!~!#%&*() \r\n \r\n");
		String vv = getUrlParameterEncodeStr("三星\\");
		System.out.println(v);
		System.out.println(vv);
		String v1 = UrlParameterEncoder.getUrlParameterDecodeStr(v);
		String vv1 = UrlParameterEncoder
				.getUrlParameterDecodeStr("%E4%B8%89%E6%98%9F%26%2392%3B");
		System.out.println(v1);
		System.out.println(vv1);
		System.out.println("ddd");
	}
}
