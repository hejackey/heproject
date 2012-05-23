package com.liu.index;
/*
 * JE�ִ� 
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;

public class JEpart {

	/**
	 * @author liuxueyong
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "埃德加斯诺在一九五年出生美国坎萨斯城一个贫苦家庭他年轻时" +
				"当过农民铁路工人印刷学徒" +
				"大学毕业以后他开始毕业所从事新闻工作坎萨斯城星报纽约太阳报初露头角" +
				"往后他在开外洋货船上当了海员历游中美洲" +
				"最后到了夏威夷仍然美国一些报纸供稿一九二八年中国大革命陷入低潮时候他到" +
				"上海担任密勒氏评论报助理编辑以后兼任纽约太阳报伦敦每日先驱报特约通信员" +
				"一九三年以后他采集新闻遍访中国主要城市东三省内蒙古台湾以及日本朝鲜荷属东印度" +
				"他在中国西南各省作长时间旅行徒步经过云南省西部到达缅甸印度访问甘地其他印度革命领袖" +
				"一九三一年九一八事变时斯诺正在上海目睹一九三二年淞沪战争" +
				"一九三三年热河战争在这以后他在北平燕京大学担任新闻系教授两年同时学习中国语文" +
				"在这时期他认识美国著名进步新闻记者史沫特莱还和鲁迅" +
				"宋庆龄以及一些中国地下党员有所接触他编译一部英文现代中国短篇小说选活的中国首先鲁迅著作介绍西方的人之一,天音通友利通";
		String type = "����ͨ����华硕多普达U608明基西门子视频播放";
		MMAnalyzer analyzer = new MMAnalyzer();
		
		//ChineseAnalyzer cn = new ChineseAnalyzer();
		
		
		FileReader reader = null;
		try {
			reader = new FileReader("E:\\product.dic");
		}
		catch( FileNotFoundException e ) {
			e.printStackTrace();
		}
		
//		try {
//			TokenStream ts = cn.tokenStream(type, reader);
//			System.out.println( ts.next() );
//		}
//		catch( IOException ex ) {
//			ex.printStackTrace();
//		}
		
		
		//System.out.println( dictionary_file );
		analyzer.addDictionary(reader);
		
		
		MMAnalyzer.addWord( "����˹��天音" );
		//MMAnalyzer.addWord( "����" );
		try {
			System.out.println( analyzer.segment( test, " | " ) );
			System.out.println( analyzer.segment( type, "|" ) );
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}
}
