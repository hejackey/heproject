package com.sohu.spaces.solr.client;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * solr创建索引客户端
 * @author xiaolianghe
 *
 */
public class SolrIndexClient {
    private static Logger log = LoggerFactory.getLogger(SolrIndexClient.class);
    private static EmbeddedSolrServer embeddSolrServer;
    private static HttpSolrServer httpSolrServer;
    
    /**
     * 获取EmbeddedSolrServer对象
     * @param solrHome  solr.home
     * @param coreName  solr.xml中配置的corename
     * @return EmbeddedSolrServer对象
     */
    public static synchronized EmbeddedSolrServer getEmbeddedSolrServer(String solrHome,String coreName){
        if (embeddSolrServer != null) {
            return embeddSolrServer;
        } else {
            try {
                log.info("solrhome====>"+solrHome+",coreName=====>"+coreName);
                
                System.setProperty("solr.solr.home", solrHome);
                CoreContainer.Initializer initializer = new CoreContainer.Initializer();
                CoreContainer coreContainer = initializer.initialize();
                
                embeddSolrServer = new EmbeddedSolrServer(coreContainer, coreName);
                
                return embeddSolrServer;
            } catch (IOException e) {
                e.printStackTrace();
                log.error("getEmbeddedSolrServer error",e.getMessage());
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                log.error("getEmbeddedSolrServer error",e.getMessage());
            } catch (SAXException e) {
                e.printStackTrace();
                log.error("getEmbeddedSolrServer error",e.getMessage());
            }
            
            return null;
        }
    }
    
    /**
     * 获取httpsolrserver对象
     * @param url   solrserver的url地址
     * @return  httpsolrserver对象
     */
    public static HttpSolrServer getHttpSolrServer(String url){
        if (httpSolrServer != null) {
            return httpSolrServer;
        } else {
            httpSolrServer = new HttpSolrServer( url );
            httpSolrServer.setSoTimeout(3000); 
            httpSolrServer.setConnectionTimeout(100);
            httpSolrServer.setDefaultMaxConnectionsPerHost(100);
            httpSolrServer.setMaxTotalConnections(100);
            httpSolrServer.setFollowRedirects(false);  
            httpSolrServer.setAllowCompression(true);
            httpSolrServer.setMaxRetries(1); 
            httpSolrServer.setParser(new XMLResponseParser()); 
            
            return httpSolrServer;
        }
    }
    
    /**
     * 提交索引
     * @param solrServerUrl 提交索引的solr实例url
     * @param docs  doc集合
     * @param server    EmbeddedSolrServer
     * @return 提交结果
     */
    public static UpdateResponse embeddedIndexCommit(String solrServerUrl,Collection<SolrInputDocument> docs,
            String solrHome,String coreName){
        try {
            getEmbeddedSolrServer(solrHome,coreName);
            
            UpdateResponse res = embeddSolrServer.add( docs );
            log.info( "add status" + res.getStatus() );
            log.info(  "add qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
            
            res = embeddSolrServer.commit();
            log.info(  "EmbeddedSolrServer commit status" + res.getStatus() );
            log.info( "EmbeddedSolrServer commit qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
            
            HttpSolrServer httpServer = new HttpSolrServer( solrServerUrl );
            res = httpServer.commit();
            log.info(  "HttpSolrServer commit status" + res.getStatus() );
            log.info(  "HttpSolrServer commit qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
            
            return res;
        } catch (SolrServerException e) {
            e.printStackTrace();
            log.error("indexCommit error",e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("indexCommit error",e.getMessage());
        }
        
       return null;
    }
    
    /**
     * 提交doc list
     * @param solrServerUrl 提交索引的solr实例url
     * @param docs  doc集合
     * @param server    EmbeddedSolrServer
     * @return
     * @throws IOException 
     * @throws SolrServerException 
     */
    public static UpdateResponse singleIndexCommit(String solrServerUrl,Collection<SolrInputDocument> docs,
            EmbeddedSolrServer server) throws SolrServerException, IOException{
            UpdateResponse res = server.commit();
            log.info(  "singleIndexCommit commit status" + res.getStatus() );
            log.info( "singleIndexCommit commit qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
            
            HttpSolrServer httpServer = new HttpSolrServer( solrServerUrl );
            res = httpServer.commit();
            log.info(  "HttpSolrServer  singleIndexCommit status" + res.getStatus() );
            log.info(  "HttpSolrServer singleIndexCommit qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
            
            return res;
    }
    
    /**
     * 添加doc list
      * @param solrServerUrl 提交索引的solr实例url
     * @param docs  doc集合
     * @param server    EmbeddedSolrServer
     * @return
     * @throws IOException 
     * @throws SolrServerException 
     */
    public static UpdateResponse singleIndexAdd(String solrServerUrl,Collection<SolrInputDocument> docs,
            EmbeddedSolrServer server) throws SolrServerException, IOException{
        
            UpdateResponse res = server.add( docs );
            log.info( "singleIndexAdd add status" + res.getStatus() );
            log.info(  "singleIndexAdd add qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
          
            return res;
    }
    
    /**
     *  http方式提交索引
     * @param docs  doc集合
     * @param url   httpsolrserver的URL地址
     * @return  更新索引结果
     */
    public static UpdateResponse httpIndexCommit(Collection<SolrInputDocument> docs,String url){
        try {
            getHttpSolrServer(url);
            UpdateResponse res = httpSolrServer.add( docs );
            log.info( "httpSolrServer add status" + res.getStatus() );
            log.info(  "httpSolrServer add qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
            
            res = httpSolrServer.commit();
            log.info(  "httpSolrServer commit status" + res.getStatus() );
            log.info( "httpSolrServer commit qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
           
            return res;
        } catch (SolrServerException e) {
            e.printStackTrace();
            log.error("httpSolrServer indexCommit error",e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("httpSolrServer indexCommit error",e.getMessage());
        }
        
       return null;
    }
}
