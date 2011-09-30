package com.bfb.transaction.engine.parserfile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.bfb.transaction.engine.util.ExecProcessUtil;


public class ParserXmlFile extends BaseParserFile {

	public void parserFile(String filePath,Map<Object,Object> para) {
		try {
			Document document = saxReader.read(new File(filePath));
			Element root = document.getRootElement();
			
			Map<String,String> rootAttribute = getElementAttribute(root);
			List<Element> subElementList = getSubElement(root);
			List<Map<String,String>> procAtrList = new ArrayList<Map<String,String>>();
			
			for(Element element : subElementList){
				if(element.getQName().getName().equals(TRAN_PROCESS)){
					procAtrList.add(getElementAttribute(element));
				}
			}
			
			//执行process中方法
			for(Map<String,String> atrMap : procAtrList){
				ExecProcessUtil.excuteProcess(atrMap, para);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

}
