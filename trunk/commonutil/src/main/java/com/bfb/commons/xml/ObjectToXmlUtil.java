package com.bfb.commons.xml;

import com.thoughtworks.xstream.XStream;
/**
 * Bean与xml转换工具类
 * @author Administrator
 *
 */
public class ObjectToXmlUtil {
	private static XStream xs = new XStream();
	/**
	 * bean转xml
	 * @param o	待转换的bean对象
	 * @return	返回xml的字符串
	 */
	public static String bean2Xml(Object o){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>\n");
		sb.append(xs.toXML(o));
		
		return sb.toString();
	}
	
	/**
	 *  bean转xml
	 * @param o	待转换的bean对象
	 * @param xsm	xstream对象，可以对节点类型进行定制，如xsm.alias("B2CRes",B2CRes.class);
	 * @return	返回xml的字符串
	 */
	public static String bean2Xml(Object o,XStream xsm){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>\n");
		sb.append(xsm.toXML(o));
		
		return sb.toString();
	}
	
	/**
	 * xml转成bean对象
	 * @param xml	待转换的xml字符串
	 * @return	返回对象
	 */
	public static Object xml2Bean(String xml){
		return xs.fromXML(xml);
	}
	
	/**
	 * xml转成bean对象
	 * @param xml	待转换xml字符串
	 * @param xsm	xstream对象，可以对节点类型进行定制，如xsm.alias("B2CRes",B2CRes.class);
	 * @return	返回对象
	 */
	public static Object xml2Bean(String xml,XStream xsm){
		return xsm.fromXML(xml);
	}
	
	public static void main(String[] arsg){
		xml2Bean("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?><com.bang.entity.UserAccountVO>  <userAccountid>MBUSER_A11011081112211118yqJ2109</userAccountid>  <id>mbuser_l11410111140040413fsk8140</id>  <operator>12312312</operator>  <bankCode>001</bankCode>  <remark></remark>  <applySum>123</applySum>  <transId>trtorder20110111595907701YNd9983</transId>  <callCode>004</callCode>  <result>sys_001</result></com.bang.entity.UserAccountVO>");
	}
}
