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
        String text = "��·͸�籨����ӡ���������������һ��Ա���ڶ�(29��)��ʾ��"
            + "�����и�������ʱ��27�ճ�5ʱ53�ַ���������6.2�������Ѿ��������5427��������"
            + "20000�������ˣ���20�����޼ҿɹ顣"; //��������

        //�����������ƥ������ķִ��㷨
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
            Query query = parser.parse("ӡ�������� 6.2������");//������
            Hits hits = isearcher.search(query);
            System.out.println("���У�" + hits.length());

            Highlighter highlighter = new Highlighter(new QueryScorer(query));
            for (int i = 0; i < hits.length(); i++)
            {
                text = hits.doc(i).get(fieldName);
                TermPositionVector tpv = (TermPositionVector) IndexReader.open(
                    directory).getTermFreqVector(hits.id(i), fieldName);
                TokenStream tokenStream = TokenSources.getTokenStream(tpv);
                String result = highlighter.getBestFragments(tokenStream, text, 3, "...");
                System.out.println("���ݣ�" + result);
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
