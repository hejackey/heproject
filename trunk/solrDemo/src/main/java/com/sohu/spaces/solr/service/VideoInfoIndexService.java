package com.sohu.spaces.solr.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.spaces.solr.client.SolrIndexClient;
import com.sohu.spaces.solr.dao.VideoInfoDaoImpl;
import com.sohu.spaces.solr.model.VideoInfo;
import com.sohu.spaces.solr.util.ConstantUtil;
import com.sohu.spaces.solr.util.DateUtil;
import com.sohu.spaces.solr.util.VideoIndexUtil;


/**
 * 视频索引服务类
 * @author xiaolianghe
 *
 */
public class VideoInfoIndexService {
    private static Logger log = LoggerFactory.getLogger(VideoInfoIndexService.class);
    private static EmbeddedSolrServer server01 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_01);
    private static EmbeddedSolrServer server02 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_02);
    private static EmbeddedSolrServer server03 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_03);
    private static EmbeddedSolrServer server04 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_04);
    private static EmbeddedSolrServer server05 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_05);
    private static EmbeddedSolrServer server06 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_06);
    private static EmbeddedSolrServer server07 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_07);
    private static EmbeddedSolrServer server08 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_08);
    private static EmbeddedSolrServer server09 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_09);
    private static EmbeddedSolrServer server10 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_10);
    private static EmbeddedSolrServer server11 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_11);
    private static EmbeddedSolrServer server12 = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_MULTI_SOLR_CORE_NAME_12);
    
    private static String serverUrl01 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE01;
    private static String serverUrl02 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE02;
    private static String serverUrl03 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE03;
    private static String serverUrl04 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE04;
    private static String serverUrl05 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE05;
    private static String serverUrl06 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE06;
    private static String serverUrl07 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE07;
    private static String serverUrl08 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE08;
    private static String serverUrl09 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE09;
    private static String serverUrl10 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE10;
    private static String serverUrl11 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE11;
    private static String serverUrl12 = ConstantUtil.HTTP_SOLR_SERVER_URL_CORE12;
    
    private static VideoInfoIndexService instance = null;
    private VideoInfoDaoImpl dao;
    private SolrServer solrServer = null;
    
    private VideoInfoIndexService(){
        
    }
    
    public synchronized static VideoInfoIndexService getInstantce(){
        if (instance != null) {
            return instance;
        } else {
            return new VideoInfoIndexService(); 
        }
    }
    
    /**
     * 更新指定视频id索引
     * @param vid
     * @return UpdateResponse更新结果
     */
    public UpdateResponse updateVideoIndex(Long vid){
        if (vid == null || vid == 0) {
            log.error("updateVideoIndex vid is null" );
            return null;
        }
        
        dao = VideoInfoDaoImpl.getInstantce();
        if (dao == null) {
            log.error(" VideoInfoDaoImpl.getInstantce is null");
            return null;
        }
        
        try {
            VideoInfo videoInfo = dao.getVideoInfoById(vid);
            if (videoInfo == null) {
                log.info("video is not exits ,vid=====>"+vid);
                return null;
            }
            
            List<VideoInfo> videoInfoList = new ArrayList<VideoInfo>();
            videoInfoList.add(videoInfo);
            
            Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
            docs = VideoIndexUtil.putDataToDoc(videoInfoList,docs); 
            
            if ( ConstantUtil.JVM_CREATE_INDEX == 1){
                log.info("create index use jvm");
                
                return SolrIndexClient.embeddedIndexCommit(ConstantUtil.HTTP_SOLR_SERVER_URL, docs, 
                        ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_SOLR_CORE_NAME);
            } else {
                log.info("create index use http");
                
                return SolrIndexClient.httpIndexCommit(docs, ConstantUtil.HTTP_SOLR_SERVER_URL);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            log.error("updateVideoIndex exception:" + e.getMessage()+",vid=====>"+vid);
            
            return null;
        }
    }
    
    public void delVideoIndex(Long vid){
        try {
            
            
            if ( ConstantUtil.JVM_CREATE_INDEX == 1){
                log.info("create index use jvm");
                solrServer = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_SOLR_CORE_NAME);
            } else {
                log.info("create index use http");
                solrServer = SolrIndexClient.getUgcHttpSolrServer(ConstantUtil.HTTP_SOLR_SERVER_URL);
            }
            
            /*UpdateRequest req = new UpdateRequest();
            req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
            req.deleteById("U"+vid);
            UpdateResponse res = req.process( solrServer );*/
            solrServer.deleteById("U"+vid);
            solrServer.commit();
             
            solrServer = new HttpSolrServer( ConstantUtil.HTTP_SOLR_SERVER_URL );
            UpdateResponse res = solrServer.commit();
            System.out.println(res);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 全量视频建索引
     */
    public void allVideoInfoIndex(){
        try {
            VideoInfoDaoImpl dao = VideoInfoDaoImpl.getInstantce();
            String date = ConstantUtil.ALL_INDEX_END_DATE;
            Date sdate =DateUtil.formatStr2Date(date, "yyyy-MM-dd");
            Date initDate = DateUtil.formatStr2Date(ConstantUtil.ALL_INDEX_START_DATE, "yyyy-MM-dd");
            Date edate = null;
            
            long time = System.currentTimeMillis();
            int hjCount = 0;
            long time0 = 0;
            long time1 = 0;
            long time2 = 0;
            long time3 = 0;
            long time4 = 0;
            long time5 = 0;
            long time6 = 0;
            int count = 0;
            int page = 0;

            EmbeddedSolrServer server = null;//SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_SOLR_CORE_NAME);
            String sorlServerUrl = null;//ConstantUtil.HTTP_SOLR_SERVER_URL;
            String strDate = null;
            
            while(true){
                //sdate小于数据库中视频最小修改日期后，退出
                if (DateUtil.compDateTime(sdate,initDate )){
                    break;
                }
                
                time0 = System.currentTimeMillis();
                edate = DateUtil.formatStr2Date( DateUtil.formatDate2Str(sdate, "yyyy-MM-dd") + " 23:59:59" ,"yyyy-MM-dd HH:mm:ss");
                count = dao.countVideoInfo(sdate, edate);
                hjCount +=count;
                
                time1 = System.currentTimeMillis();
                System.out.println("countVideoInfo size =====>"+count+",use times=====>"+(time1-time0)/1000);
                
                if (count > 0) {
                     page = VideoIndexUtil.getPage(count);
                     
                    strDate = DateUtil.formatDate2Str(sdate, "yyyy-MM-dd");
                    for (int i=1;i<=page;i++) {
                        time2 = System.currentTimeMillis();
                        List<VideoInfo> videoInfoList = dao.getVideoInfo(sdate, edate, i, ConstantUtil.INDEX_PAGE_SIZE);
                        time3 = System.currentTimeMillis();
                        System.out.println(strDate+","+i+"====>get date use times=====>"+(time3-time2)/1000);
                       
                        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
                        docs = VideoIndexUtil.putDataToDoc(videoInfoList,docs);
                        
                       
                        time4 = System.currentTimeMillis();
                        
                        server = getCoreSolrServerByMonth(sdate.getMonth());
                        sorlServerUrl = getCoreSolrServerUrlByMonth(sdate.getMonth());
                        
                        SolrIndexClient.singleIndexAdd(sorlServerUrl, docs, server);
                        SolrIndexClient.singleIndexCommit(sorlServerUrl, docs, server);
                        
                        time5 = System.currentTimeMillis();
                        
                        System.out.println(strDate+","+i+"====>add docs size=====>"+docs.size()+",use times=====>"+(time5-time4)/1000);
                    }
                    
                    time6 = System.currentTimeMillis();
                    System.out.println(strDate+","+"add docs size=====>"+count+",use times=====>"+(time6-time0)/1000);
                }
                
                //循环取前一天数据开始建索引
                sdate = DateUtil.addMinDay(sdate, -1);
            }
            
            long etime = System.currentTimeMillis();
            
            System.out.println("all add docs size=====>"+hjCount+",use times=====>"+(etime-time)/1000);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("system err,"+e.getMessage());
        } catch (SolrServerException e) {
            e.printStackTrace();
            System.out.println("system err,"+e.getMessage());
        } catch (IOException e) {
            System.out.println("system err,"+e.getMessage());
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    private static Map<Integer,EmbeddedSolrServer>  map= new HashMap<Integer,EmbeddedSolrServer>();
    static{
        map.put(1, server01);
        map.put(2, server02);
        map.put(3, server03);
        map.put(4, server04);
        map.put(5, server05);
        map.put(6, server06);
        map.put(7, server07);
        map.put(8, server08);
        map.put(9, server09);
        map.put(10, server10);
        map.put(11, server11);
        map.put(12, server12);
    }
    
    private static Map<Integer,String>  urlMap= new HashMap<Integer,String>();
    static{
        urlMap.put(1, serverUrl01);
        urlMap.put(2, serverUrl02);
        urlMap.put(3, serverUrl03);
        urlMap.put(4, serverUrl04);
        urlMap.put(5, serverUrl05);
        urlMap.put(6, serverUrl06);
        urlMap.put(7, serverUrl07);
        urlMap.put(8, serverUrl08);
        urlMap.put(9, serverUrl09);
        urlMap.put(10, serverUrl10);
        urlMap.put(11, serverUrl11);
        urlMap.put(12, serverUrl12);
    }
    
    private EmbeddedSolrServer getCoreSolrServerByMonth(int month){
        return map.get(month+1);
    }
    
    private String getCoreSolrServerUrlByMonth(int month){
        return urlMap.get(month+1);
    }
}
