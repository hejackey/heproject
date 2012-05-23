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

public void createIndex() throws IOException {   // �÷�����������
   IndexWriter writer = new IndexWriter(indexPath,analyzer,true);
   Document docA = new Document();
   String fileTextA = "��Ϊ����������ȼ������ʧ��̫�����µ�ƽ�ߵ�ʱ�̣�Ȼ�������������Ȼ��������û��˭����������ʱ��ľ�Ƭ���˸������Ϊ���ø����԰��������ʸС�";
   Field fieldA = new Field("contents", fileTextA, Field.Store.YES,Field.Index.TOKENIZED);
   docA.add(fieldA); 
  
   Document docB = new Document();
   String fileTextB = "��Ϊ�������˺�Ϊ���۵������羰�������˲��ɵ��㷲�����������Ϯ��������ֻ��ǲ�ʹ�ֻ������ѣ�û��˭���ܹ���������Ȼ����Ϊģ������˺���ѷε����ź���";
   Field fieldB = new Field("contents", fileTextB, Field.Store.YES,Field.Index.TOKENIZED);
   docB.add(fieldB); 
  
   Document docC = new Document();
   String fileTextC = "��ϲ������һ���˹¶������Σ������뺣��Ľ��ӵش�����ȼ���š�"+
   "��Ϊ��һ���¶�����ϲ�����˻������ɫ�����ǻ��Ƶز����߼���";
   Field fieldC = new Field("contents", fileTextC, Field.Store.YES,Field.Index.TOKENIZED);
   docC.add(fieldC); 
  
   writer.addDocument(docA);
   writer.addDocument(docB);
   writer.addDocument(docC);
   writer.optimize();
   writer.close();
}

public void search(String fieldName,String keyword) throws IOException, ParseException{   // �����ķ�������ʵ�ָ�����ʾ
   searcher = new IndexSearcher(indexPath); 
   QueryParser queryParse = new QueryParser(fieldName, analyzer);     //   ����QueryParser�������û�����ļ����ؼ���
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
                System.out.println("�������ʾ�� "+(i+1) +" ���������������ʾ��"); 
                System.out.println(highLightText);    
            } 
   }
   searcher.close();
}


public static void main(String[] args) {    // ����������
   MyHighLighter mhl = new MyHighLighter();
   try {
    mhl.createIndex();
    mhl.search("contents", "��Ϊ");
   } catch (IOException e) {
    e.printStackTrace();
   } catch (ParseException e) {
    e.printStackTrace();
   }
}

}


