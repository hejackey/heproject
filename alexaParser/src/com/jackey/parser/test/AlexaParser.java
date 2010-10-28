package com.jackey.parser.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.jackey.parser.bean.AlexBean;
import com.jackey.parser.dao.AlexaParserDao;
import com.jackey.parser.dao.DbConnection;

/**
 * 抓取alexa上国内排名前二十网站
 * @author he
 *
 */
public class AlexaParser {
	private static AlexaParserDao dao=null;
	/**
	 * 解析国际alexa模版
	 * @param url
	 * @param sorttype
	 */
	public static void parserAlexa(String url,int sorttype){
		try {
			Parser parser = new Parser(url);
			NodeList nodeList = parser.parse(new TagNameFilter("li"));
			
			Node[] nodeArray = nodeList.toNodeArray();
			for(Node node : nodeArray){				
				if(node.getText().equals("li class=\"site-listing\"")){					
					AlexBean bean = new AlexBean();
					bean = formatNodeList(node.getChildren(),"div","div class=\"count\"","count",bean);
					bean.setSorttype(sorttype);
					
					try {						
						dao=new AlexaParserDao(DbConnection.getDBConnection());					
						
						int id = dao.query(bean);
						if(id <=0 )
							dao.insert(bean);
						else{
							bean.setId(id);
							dao.update(bean);							
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}					
				}
			}
			
			System.out.println("parser html end");
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param nodeList	按tag解析后的nodelist
	 * @param tag		解析标识符
	 * @param tagContent	解析后的节点内容
	 * @param prop		对应到bean中属性
	 * @param bean		bean
	 * @return
	 */
	public static AlexBean formatNodeList(NodeList nodeList,String tag,String tagContent,String prop,AlexBean bean){
		nodeList = nodeList.extractAllNodesThatMatch(new TagNameFilter(tag));
		Node[] aNode= nodeList.toNodeArray();
		for(Node node : aNode){
			if(node.getText().equals(tagContent)){
				if("count".equals(prop))
					bean.setCount(Integer.valueOf(node.getChildren().toHtml()));
				else if("domain".equals(prop))
					bean.setDomain(node.getChildren().toHtml());
				else if("desc".equals(prop))
					bean.setDesc(node.getChildren().toHtml().substring(0,node.getChildren().toHtml().indexOf("<span")));
				else if("title".equals(prop))
					bean.setTitle(node.getChildren().toHtml());
				else{
					formatNodeList(node.getChildren(),"a","a href=\"/siteinfo/"+bean.getDomain().toLowerCase()+"\"","title",bean);
				}
				
			}
			else{
				formatNodeList(node.getChildren(),"span","span class=\"small topsites-label\"","domain",bean);
				formatNodeList(node.getChildren(),"div","div class=\"description\"","desc",bean);
				
				formatNodeList(node.getChildren(),"h2","h2","",bean);
			}
		}
		
		return bean;
	}
	
	/**
	 * 解析国内alexa模版
	 * @param url
	 * @param sorttype
	 */
	public static void parserGnAlexa(String url,int sorttype,String webtype){
		try {
			Parser parser = new Parser(url);
			NodeList nodeList = parser.parse(new TagNameFilter("li"));
			
			Node[] nodeArray = nodeList.toNodeArray();
			for(Node node : nodeArray){				
				if(node.getText().equals("li class=\"site-listing\"")){					
					AlexBean bean = new AlexBean();
					bean = formatGnNodeList(node.getChildren(),"div","div class=\"count\"","count",bean);
					bean.setSorttype(sorttype);
					bean.setWebtype(webtype);
					bean.setDomain(bean.getDomain().replace("/",""));
					bean.setTitle(bean.getDomain().replace("/",""));
					
					try {						
						dao=new AlexaParserDao(DbConnection.getDBConnection());					
						
						int id = dao.query(bean);
						if(id <=0 )
							dao.insert(bean);
						else{
							bean.setId(id);
							dao.update(bean);							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
			}
			
			System.out.println("parser html end");
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param nodeList	按tag解析后的nodelist
	 * @param tag		解析标识符
	 * @param tagContent	解析后的节点内容
	 * @param prop		对应到bean中属性
	 * @param bean		bean
	 * @return
	 */
	public static AlexBean formatGnNodeList(NodeList nodeList,String tag,String tagContent,String prop,AlexBean bean){
		nodeList = nodeList.extractAllNodesThatMatch(new TagNameFilter(tag));
		Node[] aNode= nodeList.toNodeArray();
		for(Node node : aNode){
			if(node.getText().contains(tagContent)){
				if("count".equals(prop))
					bean.setCount(Integer.valueOf(node.getChildren().toHtml()));
				else if("domain".equals(prop))
					bean.setDomain(node.getChildren().toHtml());
				else if("desc".equals(prop))
					bean.setDesc(node.getChildren().toHtml().substring(0,node.getChildren().toHtml().indexOf("<span")));
				else if("title".equals(prop))
					bean.setTitle(node.getChildren().toHtml());
				else{
					formatGnNodeList(node.getChildren(),"a","a href=\"/siteinfo/","domain",bean);
				}
				
			}
			else{
				formatGnNodeList(node.getChildren(),"div","div class=\"description\"","desc",bean);
				
				formatGnNodeList(node.getChildren(),"h2","h2","",bean);
			}
		}
		
		return bean;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//国内alexa排名前100网站,其他分页url=http://www.alexa.com/topsites/countries;1/CN
		parserAlexa("http://www.alexa.com/topsites/countries/CN",1);	
		//国际alexa排名,其他分页url=http://www.alexa.com/topsites/global;4
		//parserAlexa("http://www.alexa.com/topsites/global;24",2);	
		/*try {
			String srcUrl = "http://cn.alexa.com/topsites/category;5/Top/World/Chinese_Simplified/%E8%B4%AD%E7%89%A9";
			String url = URLDecoder.decode(srcUrl,"utf-8");
			int last = url.lastIndexOf("/");
			System.out.println(url.substring(last+1));
			
			parserGnAlexa(srcUrl,1,url.substring(last+1));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}

}
