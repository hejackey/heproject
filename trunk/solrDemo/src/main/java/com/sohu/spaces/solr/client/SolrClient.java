package com.sohu.spaces.solr.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.xml.sax.SAXException;

/**
 * 
 * @author xiaolianghe
 *
 */
public class SolrClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //String url = "http://10.11.132.63:8983/solr";
        String url =  "http://localhost:8080/solr/core1";
        //addIndex(url);
        
        queryIndex(url);   
        //String emburl = "http://localhost:8080/solr/core0/";
        //addIndex(emburl);//core0的schemal中无price字段，addfireld不能加price
        addIndexByEmbedd(url);
    }
    
    public static void addIndex(String url){
        HttpSolrServer server = new HttpSolrServer( url );
        server.setSoTimeout(1000);  // socket read timeout
        server.setConnectionTimeout(100);
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);  // defaults to false
        server.setAllowCompression(true);
        server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
        server.setParser(new XMLResponseParser()); // binary parser is used by default
   
        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField( "id", "id1", 1.0f );
        doc1.addField( "name", "doc1", 1.0f );
        doc1.addField( "price", 10 );
        
        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField( "id", "id2", 1.0f );
        doc2.addField( "name", "doc2", 1.0f );
        doc2.addField( "price", 20 );
        
        SolrInputDocument doc3 = new SolrInputDocument();
        doc3.addField( "id", "id3", 1.0f );
        doc3.addField( "name", "doc2", 1.0f );
        doc3.addField( "price", 20 );
   
   
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add( doc1 );
        docs.add( doc2 );
        docs.add( doc3 );
        
        try {
            UpdateResponse res =  server.add( docs );
            System.out.println( "add status" + res.getStatus() );
            System.out.println( "add qtime " + res.getQTime() );
            
            res = server.commit();
            System.out.println( "commit status" + res.getStatus() );
            System.out.println( "commit qtime " + res.getQTime() );
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void queryIndex(String url){
        try {
            SolrServer server = new HttpSolrServer( url );
            
            SolrQuery query = new SolrQuery();
            query.setQuery( "name:doc2 AND id:id1" );
           // query.setQuery( "name:doc2 OR id:id2" );
            query.addSortField( "id", SolrQuery.ORDER.asc );
            
            QueryResponse rsp = server.query( query );
            SolrDocumentList docs = rsp.getResults();
            System.out.println(docs.size());
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
    
    public static void addIndexByEmbedd(String url){
        try {
             System.setProperty("solr.solr.home", "D:\\solr_home");
            CoreContainer.Initializer initializer = new CoreContainer.Initializer();
            CoreContainer coreContainer = initializer.initialize();
            EmbeddedSolrServer server = new EmbeddedSolrServer(coreContainer, "core1");
            
           /*File home = new File( "D:\\solr_home" );
            File f = new File( home, "solr.xml" );
            CoreContainer container = new CoreContainer("D:\\solr_home",new File("D:\\solr_home\\solr.xml"));
            container.load( "D:\\solr_home", f );

            EmbeddedSolrServer server = new EmbeddedSolrServer( container, "core0" );*/
            
            SolrInputDocument doc1 = new SolrInputDocument();
            doc1.addField( "id", "id1", 1.0f );
            doc1.addField( "name", "doc1", 1.0f );
            //doc1.addField( "price", 10 );
            
            SolrInputDocument doc2 = new SolrInputDocument();
            doc2.addField( "id", "id2", 1.0f );
            doc2.addField( "name", "doc中国2 人", 1.0f );
           // doc2.addField( "price", 20 );
            
            SolrInputDocument doc3 = new SolrInputDocument();
            doc3.addField( "id", "id3", 1.0f );
            doc3.addField( "name", "doc2中国人", 1.0f );
            //doc3.addField( "price", 20 );
       
       
            Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
            docs.add( doc1 );
            docs.add( doc2 );
            docs.add( doc3 );
            
            UpdateResponse res =  server.add( docs );
            System.out.println( "add status" + res.getStatus() );
            System.out.println( "add qtime " + res.getQTime() );
            
            res = server.commit();
            System.out.println( "commit status" + res.getStatus() );
            System.out.println( "commit qtime " + res.getQTime() );
            
            HttpSolrServer server1 = new HttpSolrServer( url );
            res = server1.commit();
            System.out.println( "commit status" + res.getStatus() );
            System.out.println( "commit qtime " + res.getQTime() );
            
           System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
     
    }
}
