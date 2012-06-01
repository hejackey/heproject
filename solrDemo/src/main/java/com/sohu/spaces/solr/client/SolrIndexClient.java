package com.sohu.spaces.solr.client;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
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
    
    /**
     * 获取EmbeddedSolrServer对象
     * @param solrHome  solr.home
     * @param coreName  solr.xml中配置的corename
     * @return EmbeddedSolrServer对象
     */
    public static EmbeddedSolrServer getEmbeddedSolrServer(String solrHome,String coreName){
        try {
            log.info("solrhome====>"+solrHome+",coreName=====>"+coreName);
            
            System.setProperty("solr.solr.home", solrHome);
            CoreContainer.Initializer initializer = new CoreContainer.Initializer();
            CoreContainer coreContainer = initializer.initialize();
            
           return new EmbeddedSolrServer(coreContainer, coreName);
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
    
    /**
     * 提交索引
     * @param solrServerUrl 提交索引的solr实例url
     * @param docs  doc集合
     * @param server    EmbeddedSolrServer
     * @return 提交结果
     */
    public static UpdateResponse embeddedIndexCommit(String solrServerUrl,Collection<SolrInputDocument> docs,
            EmbeddedSolrServer server){
        try {
            UpdateResponse res = server.add( docs );
            log.info( "add status" + res.getStatus() );
            log.info(  "add qtime " + res.getQTime() );
            if(res.getStatus() != 0 )
                return null;
            
            res = server.commit();
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
}
