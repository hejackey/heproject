package com.sohu.spaces.solr.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.StringUtils;

import com.sohu.spaces.solr.client.SolrIndexClient;
import com.sohu.spaces.solr.model.QueryVideoInfo;
import com.sohu.spaces.solr.util.ConstantUtil;
import com.sohu.spaces.solr.util.HttpClientUtil;
import com.sohu.spaces.solr.util.HttpConnectionManager;
import com.sohu.spaces.solr.util.ResponseUtil;
import com.sohu.spaces.solr.util.VideoIndexUtil;

/**
 * 视频查询servlet接口
 * @author xiaolianghe
 *
 */
public class VideoInfoServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6631086997410377006L;
    private static Logger log = LoggerFactory.getLogger(VideoInfoServlet.class);
    
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        doGet(request,response);
    }
    
    public void doGet(HttpServletRequest request,HttpServletResponse response){
        
        try {
            QueryVideoInfo qVideoInfo = new QueryVideoInfo();
            qVideoInfo = putDataToQueryVideoInfo(request,qVideoInfo);
            
            SolrQuery query = new SolrQuery();
            query = setQueryCond(query,qVideoInfo);
            
            SolrServer server = SolrIndexClient.getHttpSolrServer(ConstantUtil.HTTP_SOLR_SERVER_URL);
            QueryResponse rsp = server.query( query );
            
            if (rsp != null) {
                SolrDocumentList docs = rsp.getResults();
                
                JSONArray jsonArray = new JSONArray();  
                jsonArray =  JSONArray.fromObject(docs);
                
                JSONObject object = new JSONObject();
                object.element("result", jsonArray);
                System.out.println(object.toString());
                
                ResponseUtil.responseWrite(response,object.toString());

                return;
            } else {
                log.info("QueryResponse is null");
                ResponseUtil.responseWrite(response, "");
                
                return;
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            ResponseUtil.responseWrite(response, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ugc请求参数封装对象
     * @param request
     * @param qVideoInfo
     * @return
     */
    private QueryVideoInfo putDataToQueryVideoInfo(HttpServletRequest request,QueryVideoInfo qVideoInfo){
        String cCovertStatus = request.getParameter("cCovertStatus");
        if (!StringUtils.isNullOrEmpty(cCovertStatus)) {
            qVideoInfo.setcCovertStatus(Integer.valueOf(cCovertStatus));
        }
        
        String cPubStatus = request.getParameter("cPubStatus");
        if (!StringUtils.isNullOrEmpty(cPubStatus)) {
            qVideoInfo.setcPubStatus(Integer.valueOf(cPubStatus));
        }
        
        String cValid = request.getParameter("cValid");
        if (!StringUtils.isNullOrEmpty(cValid)) {
            qVideoInfo.setcValid(Integer.valueOf(cValid));
        }
        
        String cUploadStatus = request.getParameter("cUploadStatus");
        if (!StringUtils.isNullOrEmpty(cUploadStatus)) {
            qVideoInfo.setcUploadStatus(Integer.valueOf(cUploadStatus));
        }
        
        String uploadSource = request.getParameter("uploadSource");
        if (!StringUtils.isNullOrEmpty(uploadSource)) {
            qVideoInfo.setUploadSource(Integer.valueOf(uploadSource));
        }
        
        String uStatus = request.getParameter("uStatus");
        if (!StringUtils.isNullOrEmpty(uStatus)) {
            Integer status = Integer.valueOf(uStatus);
            qVideoInfo.setuStatus(status);
            qVideoInfo.setcCovertStatus(VideoIndexUtil.getCovertStatus(status));
            qVideoInfo.setcPubStatus(VideoIndexUtil.getPubStatus(status));
            qVideoInfo.setcUploadStatus(VideoIndexUtil.getUploadStatus(status));
            qVideoInfo.setcValid(VideoIndexUtil.getValidStatus(status));
        }
       
        String start = request.getParameter("start");
        if (!StringUtils.isNullOrEmpty(start)) {
            qVideoInfo.setStart(Integer.valueOf(start));
        } else {
        	 qVideoInfo.setStart(0);
        }
        
        String limit = request.getParameter("limit");
        if (!StringUtils.isNullOrEmpty(limit)) {
            qVideoInfo.setLimit(Integer.valueOf(limit));
        } else {
        	 qVideoInfo.setLimit(ConstantUtil.QUERY_PAGE_SIZE);
        }
        
        qVideoInfo.setUploadUserName(request.getParameter("uploadUserName"));
        qVideoInfo.setSort(request.getParameter("sort"));
        qVideoInfo.setUploadIp(request.getParameter("uploadIp"));
        qVideoInfo.setKey(request.getParameter("key"));
        qVideoInfo.setCateCode(request.getParameter("cateCode"));
        qVideoInfo.setTag(request.getParameter("tag"));
        qVideoInfo.setTitle(request.getParameter("title"));
        qVideoInfo.setcSource(request.getParameter("cSource"));
        
        return qVideoInfo;
    }
    
    /**
     * 封装ugc查询条件
     * @param query
     * @param qVideoInfo
     * @return
     */
    private SolrQuery setQueryCond(SolrQuery query,QueryVideoInfo qVideoInfo){
        if ( !StringUtils.isNullOrEmpty(qVideoInfo.getKey())) {//关键词模糊查询
            query.setQuery( "c_name:" +qVideoInfo.getKey());
            query.setQuery( "u_tag:" +qVideoInfo.getKey() );
            query.setQuery( "u_introduction:" +qVideoInfo.getKey());
        } 
        
        if (!StringUtils.isNullOrEmpty(qVideoInfo.getTag())) {
            query.setQuery("u_tag_exact:"+qVideoInfo.getTag());
        }
        
        if (!StringUtils.isNullOrEmpty(qVideoInfo.getTitle())) {
            query.setQuery("c_name_exact:"+qVideoInfo.getTitle());
        }
       
        if (qVideoInfo.getUploadSource() != null) {
            query.setQuery( "c_uploadfrom:"+qVideoInfo.getUploadSource() );
        }
        
        if (!StringUtils.isNullOrEmpty(qVideoInfo.getUploadIp())) {
            query.setQuery( "u_uploadip:"+qVideoInfo.getUploadIp() );
        }
        
        if (!StringUtils.isNullOrEmpty(qVideoInfo.getCateCode())) {
            query.setQuery( "c_category:"+qVideoInfo.getCateCode() );
        }
        
        if (qVideoInfo.getcUploadStatus() != null) {
            query.setQuery( "c_upload_status:"+qVideoInfo.getcUploadStatus() );
        }
        
        if (qVideoInfo.getcCovertStatus() != null) {
            query.setQuery( "c_covert_status:"+qVideoInfo.getcCovertStatus() );
        }
        
        if (qVideoInfo.getcPubStatus() != null) {
            query.setQuery( "c_pub_status:"+qVideoInfo.getcPubStatus() );
        }
        
        if (qVideoInfo.getcValid() != null) {
            query.setQuery( "c_valid:"+qVideoInfo.getcValid() );
        }
        
        if (!StringUtils.isNullOrEmpty(qVideoInfo.getUploadUserName())) {
            HttpGet httpGet = null;
            try {
                String url=ConstantUtil.HTTP_GET_USER_URL;
                Map<String,String> paramMap = new HashMap<String,String>();
                paramMap.put("passport",qVideoInfo.getUploadUserName());
                httpGet = HttpClientUtil.getHttpGet(url, paramMap, HTTP.UTF_8);
                
                HttpClient httpClient = HttpConnectionManager.getHttpClient();
                HttpResponse httpResponse = httpClient.execute(httpGet);
                
                if (httpResponse != null && httpResponse.getStatusLine().getStatusCode()==200) {
                    String res = EntityUtils.toString(httpResponse.getEntity());
                        
                    if (!StringUtils.isNullOrEmpty(res)) {
                        JSONObject object = JSONObject.fromObject(JSONObject.fromObject(res).get("data"));
                        if ( 1 == object.getInt("status") ){
                            query.setQuery("c_upload_userid:"+object.get("id"));
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            finally{
                if(httpGet != null)
                    httpGet.abort();
            }
        }
        
        if (!StringUtils.isNullOrEmpty(qVideoInfo.getcSource()) ){
            query.setQuery("c_source:"+qVideoInfo.getcSource());
        } 
        
        if ("1".equals(qVideoInfo.getSort())) {
            query.addSortField( "c_uploadtime", SolrQuery.ORDER.desc );
        } else if ("2".equals(qVideoInfo.getSort())) {
            query.addSortField( "c_lastmodified", SolrQuery.ORDER.desc );
        }
        
       
        
        //设置分页条件
        query.setStart(qVideoInfo.getStart());
        query.setRows(qVideoInfo.getLimit());
        
        return query;
    }
}
