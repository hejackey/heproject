package com.bfb.transaction.engine.parserfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * �������������ļ�
 * @author Administrator
 *
 */
public abstract class BaseParserFile {
	public SAXReader saxReader;
	protected static final String TRAN_PROCESS="process";
	
	public BaseParserFile(){
		this.saxReader = getSaxReader();
	}
	
	public SAXReader getSaxReader(){
		if(saxReader == null)
			saxReader = new SAXReader();
		
		return saxReader;
	}
	
	/**
	 * �����ļ�
	 * @param filePath	�ļ�·��
	 */
	protected abstract void parserFile(String filePath,Map<Object,Object> para);
	
	/**
	 * ��ȡһ���ڵ���������ԣ�key-value�ṹ
	 * @param element �ڵ�
	 * @return	�ýڵ��������Ե�map
	 */
	public Map<String,String> getElementAttribute(Element element){
		Map<String,String> attributeMap = new HashMap<String,String>();
		
		for(Iterator<?> at = element.attributeIterator();at.hasNext();){
			Attribute attribute = (Attribute)at.next();
			attributeMap.put(attribute.getName(), attribute.getText());
		}
		
		return attributeMap;
	}
	
	/**
	 * ��ȡһ���ڵ�������ӽڵ㣬�������ӽڵ���ӽڵ�
	 * @param element	�������Ľڵ�
	 * @return	�ýڵ��һ���ӽڵ�list
	 */
	public List<Element> getSubElement(Element element){
		List<Element> elementList = new ArrayList<Element>();
		
		for(Iterator<?> it=element.elementIterator();it.hasNext();){
			Element subElement = (Element)it.next();
			
			elementList.add(subElement);
		}
		
		return elementList;
	}
}
