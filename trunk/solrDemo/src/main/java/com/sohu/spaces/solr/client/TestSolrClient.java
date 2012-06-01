package com.sohu.spaces.solr.client;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.spaces.solr.dao.VideoInfoDaoImpl;
import com.sohu.spaces.solr.model.VideoInfo;
import com.sohu.spaces.solr.util.ConstantUtil;
import com.sohu.spaces.solr.util.DateUtil;

public class TestSolrClient {
    private static Logger log = LoggerFactory.getLogger(TestSolrClient.class);
    
    public static void main(String[] args) throws DateParseException, ParseException {
        try {
            VideoInfoDaoImpl dao = new VideoInfoDaoImpl();
            //String date = DateUtil.formatDate2Str(new Date(), "yyyy-MM-dd");
            String date = "2012-05-31";
            Date sdate =DateUtil.formatStr2Date(date, "yyyy-MM-dd");
            Date initDate = DateUtil.formatStr2Date("2012-04-01", "yyyy-MM-dd");
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
            EmbeddedSolrServer server = SolrIndexClient.getEmbeddedSolrServer("D:\\solr", "collection1");
            //EmbeddedSolrServer server = SolrIndexClient.getEmbeddedSolrServer("/opt/res", "collection1");
            String sorlServerUrl = "http://localhost:8080/solr";
            //String sorlServerUrl = "http://10.11.132.207:8983/solr";
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
                     page = getPage(count);
                    
                    strDate = DateUtil.formatDate2Str(sdate, "yyyy-MM-dd");
                    for (int i=1;i<=page;i++) {
                        time2 = System.currentTimeMillis();
                        List<VideoInfo> videoInfoList = dao.getVideoInfo(sdate, edate, i, ConstantUtil.INDEX_PAGE_SIZE);
                        time3 = System.currentTimeMillis();
                        System.out.println(strDate+","+i+"====>get date use times=====>"+(time3-time2)/1000);
                       
                        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
                        docs = putDataToDoc(videoInfoList,docs);
                        
                       
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
            
        }
        System.exit(0);
    }
    
    /**
     * 把list数据封装到doc list中
     * @param videoInfoList 提取的数据
     * @param docs doclist
     * @return  doclist
     */
    private static Collection<SolrInputDocument> putDataToDoc(List<VideoInfo> videoInfoList,Collection<SolrInputDocument> docs){
        for (VideoInfo videoInfo : videoInfoList) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("c_name", videoInfo.getTitle());
            doc.addField("c_vid", videoInfo.getId());
            doc.addField("c_name_exact", videoInfo.getTitle());
            doc.addField("c_ver_vid", "U"+videoInfo.getId());
            doc.addField("c_duration", videoInfo.getVideoLength());
            doc.addField("c_version", getIndexVerType(videoInfo.getVerType()));
            doc.addField("c_uploadtime", videoInfo.getUploadTime());
            doc.addField("c_upload_userid", videoInfo.getUserId());
            doc.addField("c_picture", videoInfo.getCusCoverURL());
            doc.addField("c_picture1", videoInfo.getCusCoverURL());
            doc.addField("c_lastmodified", videoInfo.getLastModified());
            doc.addField("c_uploadfrom", videoInfo.getUploadFrom());
            doc.addField("c_video_type", ConstantUtil.VIDEO_TYPE_SHORT);
            doc.addField("c_category", videoInfo.getCateCode());
            doc.addField("c_source", ConstantUtil.VIDEO_SOURCE_UGC);
            doc.addField("c_upload_status", getUploadStatus(videoInfo.getStatus()));
            doc.addField("c_covert_status", getCovertStatus(videoInfo.getStatus()));
            doc.addField("c_pub_status",getPubStatus(videoInfo.getStatus()));
            doc.addField("c_valid", getValidStatus(videoInfo.getStatus()));
            
            doc.addField("u_tag", videoInfo.getTag());
            doc.addField("u_tag_exact", videoInfo.getTag());
            doc.addField("u_introduction", videoInfo.getIntroduction());
            doc.addField("u_size", videoInfo.getVideoSize());
            doc.addField("u_uploadip", videoInfo.getUploadIp());
            doc.addField("u_plevel", videoInfo.getPlevel());
            doc.addField("u_play_limit", videoInfo.getPlayLimit());
            
            docs.add(doc);
        }
        
        return docs;
    }
    
    private static int getPage(int count){
        int page=count/ConstantUtil.INDEX_PAGE_SIZE;
        int pageMod = count%ConstantUtil.INDEX_PAGE_SIZE;
        
        if (pageMod > 0) {
            page += 1;
        }
        
        if (page == 0) {
            page = 1;
        }
        
        return page;
    }
    /**
     * verType转换
     * @param verType
     * @return
     */
    private static Integer getIndexVerType(Integer verType){
        if (verType == null) {
            return verType;
        }
        
        if (verType == ConstantUtil.UGC_VER_TYPE_21) {
            return ConstantUtil.C_VERSION_06;
        }
        
        if( verType == ConstantUtil.UGC_VER_TYPE_31) {
                return ConstantUtil.C_VERSION_07;
        }
           
        if( verType == ConstantUtil.UGC_VER_TYPE_10) {
            return ConstantUtil.C_VERSION_03;
        }
        
        return verType;
    }
    
    /**
     * ugc状态转视频上传状态
     * @param status
     * @return
     */
    private static int getUploadStatus(int status){
        if (status == ConstantUtil.UGC_STATUS_10) {
            return ConstantUtil.UPLOAD_STATUS_0;
        }
        
        if (status == ConstantUtil.UGC_STATUS_11) {
            return ConstantUtil.UPLOAD_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_12) {
            return ConstantUtil.UPLOAD_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_20) {
            return ConstantUtil.UPLOAD_STATUS_1;
        }
        if (status == ConstantUtil.UGC_STATUS_21) {
            return ConstantUtil.UPLOAD_STATUS_1;
        }
        if (status == ConstantUtil.UGC_STATUS_22) {
            return ConstantUtil.UPLOAD_STATUS_1;
        }
        
        if (status == ConstantUtil.UGC_STATUS_30) {
            return ConstantUtil.UPLOAD_STATUS_1;
        }
        
        if (status == ConstantUtil.UGC_STATUS_40) {
            return ConstantUtil.UPLOAD_STATUS_1;
        }
        
        if (status == ConstantUtil.UGC_STATUS_37) {
            return ConstantUtil.UPLOAD_STATUS_1;
        }
        
        return ConstantUtil.UPLOAD_STATUS_0;
    }
    
    /**
     * ugc状态转视频转码状态
     * @param status
     * @return
     */
    private static Integer getCovertStatus(Integer status){
        if (status == null) {
            return status;
        }
        
        if (status == ConstantUtil.UGC_STATUS_10) {
            return ConstantUtil.COVERT_STATUS_0;
        }
        
        if (status == ConstantUtil.UGC_STATUS_11) {
            return ConstantUtil.COVERT_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_12) {
            return ConstantUtil.COVERT_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_20) {
            return ConstantUtil.COVERT_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_21) {
            return ConstantUtil.COVERT_STATUS_2;
        }
        if (status == ConstantUtil.UGC_STATUS_22) {
            return ConstantUtil.COVERT_STATUS_3;
        }
        
        if (status == ConstantUtil.UGC_STATUS_30) {
            return ConstantUtil.COVERT_STATUS_1;
        }
        
        if (status == ConstantUtil.UGC_STATUS_40) {
            return ConstantUtil.COVERT_STATUS_1;
        }
        
        if (status == ConstantUtil.UGC_STATUS_37) {
            return ConstantUtil.COVERT_STATUS_1;
        }
        
        return ConstantUtil.COVERT_STATUS_0;
    }
    
    /**
     * ugc状态转视频发布状态
     * @param status
     * @return
     */
    private static Integer getPubStatus(Integer status){
        if (status == null) {
            return status;
        }
        
        if (status == ConstantUtil.UGC_STATUS_10) {
            return ConstantUtil.PUB_STATUS_0;
        }
        
        if (status == ConstantUtil.UGC_STATUS_11) {
            return ConstantUtil.PUB_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_12) {
            return ConstantUtil.PUB_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_20) {
            return ConstantUtil.PUB_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_21) {
            return ConstantUtil.PUB_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_22) {
            return ConstantUtil.PUB_STATUS_0;
        }
        
        if (status == ConstantUtil.UGC_STATUS_30) {
            return ConstantUtil.PUB_STATUS_0;
        }
        
        if (status == ConstantUtil.UGC_STATUS_40) {
            return ConstantUtil.PUB_STATUS_1;
        }
        
        if (status == ConstantUtil.UGC_STATUS_37) {
            return ConstantUtil.PUB_STATUS_0;
        }
        
        return ConstantUtil.PUB_STATUS_0;
    }
    
    /**
     * ugc状态转视频有效状态
     * @param status
     * @return
     */
    private static Integer getValidStatus(Integer status){
        if (status == null) {
            return status;
        }
        
        if (status == ConstantUtil.UGC_STATUS_10) {
            return ConstantUtil.VALID_STATUS_0;
        }
        
        if (status == ConstantUtil.UGC_STATUS_11) {
            return ConstantUtil.VALID_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_12) {
            return ConstantUtil.VALID_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_20) {
            return ConstantUtil.VALID_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_21) {
            return ConstantUtil.VALID_STATUS_0;
        }
        if (status == ConstantUtil.UGC_STATUS_22) {
            return ConstantUtil.VALID_STATUS_0;
        }
        
        if (status == ConstantUtil.UGC_STATUS_30) {
            return ConstantUtil.VALID_STATUS_0;
        }
        
        if (status == ConstantUtil.UGC_STATUS_40) {
            return ConstantUtil.VALID_STATUS_1;
        }
        
        if (status == ConstantUtil.UGC_STATUS_37) {
            return ConstantUtil.VALID_STATUS_0;
        }
        
        return ConstantUtil.VALID_STATUS_0;
    }
}
