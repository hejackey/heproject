package com.sohu.spaces.solr.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
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
    private static VideoInfoIndexService instance = null;
    private VideoInfoDaoImpl dao;
    
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
            //EmbeddedSolrServer server = SolrIndexClient.getEmbeddedSolrServer("D:\\solr", "collection1");
            EmbeddedSolrServer server = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.EMBED_SOLR_HOME, ConstantUtil.EMBED_SOLR_CORE_NAME);
            String sorlServerUrl = ConstantUtil.HTTP_SOLR_SERVER_URL;
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
}
