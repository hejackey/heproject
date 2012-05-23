package com.liu.hightlight;

import java.io.IOException;
import java.io.StringReader;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

public class MyHighLighter {

private String indexPath = "F:\\index";
private MMAnalyzer analyzer;
private IndexSearcher searcher;

public MyHighLighter(){
	analyzer = new MMAnalyzer();
}

public void createIndex() throws IOException {   // 该方法建立索引
   IndexWriter writer = new IndexWriter(indexPath,analyzer,true);
   Document docA = new Document();
   String fileTextA = "因为火烧云总是燃烧着消失在太阳冲下地平线的时刻，然后便是宁静的自然的天籁，没有谁会在这样的时光的镜片里伤感自语，因为灿烂给人以安静的舒适感。";
   Field fieldA = new Field("contents", fileTextA, Field.Store.YES,Field.Index.TOKENIZED);
   docA.add(fieldA); 
  
   Document docB = new Document();
   String fileTextB = "因为带有以伤痕为代价的美丽风景总是让人不由地惴惴不安，紧接着袭面而来的抑或是病痛抑或是灾难，没有谁会能够安逸着恬然，因为模糊让人撕心裂肺地想呐喊。";
   Field fieldB = new Field("contents", fileTextB, Field.Store.YES,Field.Index.TOKENIZED);
   docB.add(fieldB); 
  
   Document docC = new Document();
   String fileTextC = "我喜欢上了一个人孤独地行游，在梦与海洋的交接地带炽烈燃烧着。"+
   "因为，一条孤独的鱼喜欢上了火焰的颜色，真是荒唐地不合逻辑。";
   Field fieldC = new Field("contents", fileTextC, Field.Store.YES,Field.Index.TOKENIZED);
   docC.add(fieldC); 
  
   writer.addDocument(docA);
   writer.addDocument(docB);
   writer.addDocument(docC);
   writer.optimize();
   writer.close();
}

public void search(String fieldName,String keyword) throws IOException, ParseException{   // 检索的方法，并实现高亮显示
   searcher = new IndexSearcher(indexPath); 
   QueryParser queryParse = new QueryParser(fieldName, analyzer);     //   构造QueryParser，解析用户输入的检索关键字
   Query query = queryParse.parse(keyword); 
   Hits hits = searcher.search(query);
   for(int i=0;i<hits.length();i++){
    Document doc = hits.doc(i);
    String text = doc.get(fieldName);
    SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");    
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(query));    
            highlighter.setTextFragmenter(new SimpleFragmenter(text.length()));       
            if (text != null) {    
                TokenStream tokenStream = analyzer.tokenStream(fieldName,new StringReader(text));    
                String highLightText = highlighter.getBestFragment(tokenStream, text); 
                System.out.println("★高亮显示第 "+(i+1) +" 条检索结果如下所示："); 
                System.out.println(highLightText);    
            } 
   }
   searcher.close();
}


public static void main(String[] args) {    // 测试主函数
   MyHighLighter mhl = new MyHighLighter();
   try {
    mhl.createIndex();
    mhl.search("contents", "因为");
   } catch (IOException e) {
    e.printStackTrace();
   } catch (ParseException e) {
    e.printStackTrace();
   }
}

}


