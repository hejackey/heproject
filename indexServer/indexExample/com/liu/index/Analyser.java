package com.liu.index;

import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class Analyser {
     private static String string = "中华人民共和国在1949年建立，从此开始了新中国的伟大篇章。";
     public static void Standard_Analyzer(String str) throws Exception{
            Analyzer analyzer = new StandardAnalyzer();         
            Reader r = new StringReader(str);         
            StopFilter sf = (StopFilter) analyzer.tokenStream("", r);
            System.out.println("=====StandardAnalyzer====");
            System.out.println("分析方法：默认没有词只有字（一元分词）");
            Token t;         
           while ((t = sf.next()) != null) {         
                  System.out.println(t.termText());         
            }       
      }
     public static void CJK_Analyzer(String str) throws Exception{
            Analyzer analyzer = new CJKAnalyzer();         
            Reader r = new StringReader(str);         
            StopFilter sf = (StopFilter) analyzer.tokenStream("", r);
            System.out.println("=====CJKAnalyzer====");
            System.out.println("分析方法:交叉双字分割（二元分词）");
            Token t;         
           while ((t = sf.next()) != null) {         
                  System.out.println(t.termText());         
            }       
      }
     public static void Chiniese_Analyzer(String str) throws Exception{
            Analyzer analyzer = new ChineseAnalyzer();         
            Reader r = new StringReader(str);         
            TokenFilter tf = (TokenFilter) analyzer.tokenStream("", r);
            System.out.println("=====chinese analyzer====");
            System.out.println("分析方法:基本等同StandardAnalyzer（一元分词）");
            Token t;         
           while ((t = tf.next()) != null) {         
                  System.out.println(t.termText());         
            }       
      }
//     public static void ik_CAnalyzer(String str) throws Exception{
////          Analyzer analyzer = new MIK_CAnalyzer();
//            Analyzer analyzer = new IK_CAnalyzer();
//            Reader r = new StringReader(str); 
//            TokenStream ts = (TokenStream)analyzer.tokenStream("", r);
//            System.out.println("=====IK_CAnalyzer====");
//            System.out.println("分析方法:字典分词,正反双向搜索");
//            Token t;    
//           while ((t = ts.next()) != null) {    
//                 System.out.println(t.termText());    
//            }    
//      }
     public static void main(String[] args) throws Exception{
            String str = string;
            System.out.println("我们测试的字符串是："+str);
           
            Standard_Analyzer(str);
            CJK_Analyzer(str);
            Chiniese_Analyzer(str);
            //ik_CAnalyzer(str);
      }

}

