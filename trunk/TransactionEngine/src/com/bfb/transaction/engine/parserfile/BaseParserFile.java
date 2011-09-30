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
 * 解析交易流程文件
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
	 * 解析文件
	 * @param filePath	文件路径
	 */
	protected abstract void parserFile(String filePath,Map<Object,Object> para);
	
	/**
	 * 获取一个节点的所有属性，key-value结构
	 * @param element 节点
	 * @return	该节点所有属性的map
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
	 * 获取一个节点的所有子节点，不包含子节点的子节点
	 * @param element	待遍历的节点
	 * @return	该节点的一级子节点list
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
