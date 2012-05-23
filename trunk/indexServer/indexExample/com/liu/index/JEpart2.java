package com.liu.index;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.TermPositionVector;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class JEpart2
{

    public static void main(String[] args)
    {
        String fieldName = "text";
        String text = "据路透社报道，印度尼西亚社会事务部一官员星期二(29日)表示，"
            + "日惹市附近当地时间27日晨5时53分发生的里氏6.2级地震已经造成至少5427人死亡，"
            + "20000余人受伤，近20万人无家可归。"; //检索内容

        //采用正向最大匹配的中文分词算法
        Analyzer analyzer = new MMAnalyzer();

        Directory directory = new RAMDirectory();
        //Directory directory = FSDirectory.getDirectory("/tmp/testindex", true);

        try
        {
            IndexWriter iwriter = new IndexWriter(directory, analyzer, true);
            iwriter.setMaxFieldLength(25000);
            Document doc = new Document();
            doc.add(new Field(fieldName, text, Field.Store.YES,
                    Field.Index.TOKENIZED,
                    Field.TermVector.WITH_POSITIONS_OFFSETS));
            iwriter.addDocument(doc);
            iwriter.close();

            IndexSearcher isearcher = new IndexSearcher(directory);
            QueryParser parser = new QueryParser(fieldName, analyzer);
            Query query = parser.parse("印度尼西亚 6.2级地震");//检索词
            Hits hits = isearcher.search(query);
            System.out.println("命中：" + hits.length());

            Highlighter highlighter = new Highlighter(new QueryScorer(query));
            for (int i = 0; i < hits.length(); i++)
            {
                text = hits.doc(i).get(fieldName);
                TermPositionVector tpv = (TermPositionVector) IndexReader.open(
                    directory).getTermFreqVector(hits.id(i), fieldName);
                TokenStream tokenStream = TokenSources.getTokenStream(tpv);
                String result = highlighter.getBestFragments(tokenStream, text, 3, "...");
                System.out.println("内容：" + result);
            }

            isearcher.close();
            directory.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
