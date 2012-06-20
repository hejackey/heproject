package com.sohu.spaces.solr.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.solr.common.SolrInputDocument;

import net.sf.json.JSONObject;

import com.mysql.jdbc.StringUtils;

import com.sohu.spaces.solr.model.VideoInfo;



/**
 * 视频建索引工具类
 * @author xiaolianghe
 *
 */
public class VideoIndexUtil {
    /**
     * 把list数据封装到doc list中
     * @param videoInfoList 提取的数据
     * @param docs doclist
     * @return  doclist
     */
    public static Collection<SolrInputDocument> putDataToDoc(List<VideoInfo> videoInfoList,Collection<SolrInputDocument> docs){
        for (VideoInfo videoInfo : videoInfoList) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("c_name", videoInfo.getTitle());
            doc.addField("c_vid", videoInfo.getId());
            doc.addField("c_name_exact", videoInfo.getTitle().trim());
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
            doc.addField("u_tag_exact", videoInfo.getTag().trim());
            doc.addField("u_introduction", videoInfo.getIntroduction());
            doc.addField("u_size", videoInfo.getVideoSize());
            doc.addField("u_uploadip", videoInfo.getUploadIp());
            doc.addField("u_plevel", videoInfo.getPlevel());
            doc.addField("u_play_limit", videoInfo.getPlayLimit());
            
            docs.add(doc);
        }
        
        return docs;
    }
    
    /**
     * verType转换
     * @param verType
     * @return
     */
    public static Integer getIndexVerType(Integer verType){
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
    public static int getUploadStatus(int status){
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
    public static Integer getCovertStatus(Integer status){
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
    public static Integer getPubStatus(Integer status){
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
    public static Integer getValidStatus(Integer status){
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
    
    /**
     * 计算分页总页数
     * @param count 总记录数
     * @return  总页数
     */
    public static int getPage(int count){
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
     * 根据登录email取userid
     * @param userName  登录email
     * @return  userid
     */
    public static Long getUserId(String userName){
        if (!StringUtils.isNullOrEmpty(userName)) {
            HttpGet httpGet = null;
            try {
                String url=ConstantUtil.HTTP_GET_USER_URL;
                Map<String,String> paramMap = new HashMap<String,String>();
                paramMap.put("passport",userName);
                httpGet = HttpClientUtil.getHttpGet(url, paramMap, HTTP.UTF_8);
                
                HttpClient httpClient = HttpConnectionManager.getHttpClient();
                HttpResponse httpResponse = httpClient.execute(httpGet);
                
                if (httpResponse != null && httpResponse.getStatusLine().getStatusCode()==200) {
                    String res = EntityUtils.toString(httpResponse.getEntity());
                        
                    if (!StringUtils.isNullOrEmpty(res)) {
                        JSONObject object = JSONObject.fromObject(JSONObject.fromObject(res).get("data"));
                        if ( 1 == object.getInt("status") ){
                            return Long.valueOf(object.get("id").toString());
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            finally{
                if(httpGet != null)
                    httpGet.abort();
            }
        }
        
        return null;
    }
}
