package com.sohu.spaces.solr.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.servlet.SolrRequestParsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sohu.spaces.solr.client.SolrIndexClient;
import com.sohu.spaces.solr.util.ConstantUtil;
import com.sohu.spaces.solr.util.ResponseUtil;
import com.sohu.spaces.solr.util.VideoIndexUtil;


/**
 * 全量视频搜索转发servlet
 * @author xiaolianghe
 *
 */
public class VideoSearchServlet extends HttpServlet {
/**
     * 
     */
    private static final long serialVersionUID = 1729929623970456510L;
    private static Logger log = LoggerFactory.getLogger(VideoSearchServlet.class);
    
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        doGet(request,response);
    }
    
    public void doGet(HttpServletRequest request,HttpServletResponse response){
        try {
            String method = request.getParameter("m");
            if (!StringUtils.isNotEmpty(method)){
                log.info("query method is null");
                
                ResponseUtil.responseWrite(response, setJsonObjectElement(ConstantUtil.RESP_STATUS_0,"","请求参数method不能为空").toString());
                
                return ;
            }
            
            String queryStr = request.getQueryString();
            if (!StringUtils.isNotEmpty(queryStr)) {
                log.info("queryStr is null");  
                ResponseUtil.responseWrite(response, setJsonObjectElement(ConstantUtil.RESP_STATUS_0,"","请求参数不能为空").toString());
                
                return ;
            }
            
            if (ConstantUtil.CALL_METHOD_COUNT.equals(method)) {//统计记录数
                SolrDocumentList docs = query(queryStr,request);
                
                if (docs != null && docs.size()>0) {
                    log.info("count =====>"+docs.getNumFound());
                    ResponseUtil.responseWrite(response, setJsonObjectElement(ConstantUtil.RESP_STATUS_1,String.valueOf(docs.getNumFound()),"查询记录数正常").toString());
                    
                    return ;
                } else {
                    log.info("docs is null");
                    ResponseUtil.responseWrite(response, setJsonObjectElement(ConstantUtil.RESP_STATUS_1,"","查询记录数正常").toString());
                    
                    return ;
                }
                    
            } else if (ConstantUtil.CALL_METHOD_LIST.matches(method)) {//查询返回结果list
                SolrDocumentList docs = query(queryStr,request);
                
                if (docs != null && docs.size()>0) {
                    JSONArray jsonArray = new JSONArray();  
                    jsonArray =  JSONArray.fromObject(docs);
                    
                    ResponseUtil.responseWrite(response, setJsonObjectElement(ConstantUtil.RESP_STATUS_1,String.valueOf(docs.size()),jsonArray).toString());
                    
                    return ;
                } else {
                    log.info("docs is null");
                    ResponseUtil.responseWrite(response, setJsonObjectElement(ConstantUtil.RESP_STATUS_1,"","查询记录数正常").toString());
                    
                    return ;
                }
            } else {
                log.info("not support method=====>"+method);
                ResponseUtil.responseWrite(response, setJsonObjectElement(ConstantUtil.RESP_STATUS_0,"","请求参数method不支持").toString());
                
                return ;
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            ResponseUtil.responseWrite(response, setJsonObjectElement(ConstantUtil.RESP_STATUS_2,"","查询发生异常").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 查询入口
     * @param queryStr
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws SolrServerException
     */
    private SolrDocumentList query(String queryStr,HttpServletRequest request) throws UnsupportedEncodingException, SolrServerException{
        queryStr = URLDecoder.decode(queryStr,"gbk");
        log.info("client queryStr====>"+queryStr);
        
        //ugc根据上传用户名查询
        String userName = request.getParameter("userName");
        if (StringUtils.isNotEmpty(userName) 
                && queryStr.indexOf("c_source:UGC") != -1){
            Long userId = VideoIndexUtil.getUserId(userName);
            if (userId != null) {
                queryStr = queryStr.replaceFirst("&", "  c_upload_userid:"+userId+"&");
            }
        }
        
        SolrServer server = null;
        if (queryStr.indexOf("c_source:UGC") != -1) {
            server = SolrIndexClient.getUgcHttpSolrServer(ConstantUtil.HTTP_SOLR_SERVER_URL);
        } else if (queryStr.indexOf("c_source:VRS") != -1) {
            server = SolrIndexClient.getVrSHttpSolrServer(ConstantUtil.HTTP_SOLR_SERVER_URL_VRS);
        } else {//sharding 方式查询2个分片
            queryStr = ConstantUtil.HTTP_SOLR_SERVER_SHARDING_PARAM.replace("${queryStr}", queryStr);
            server = SolrIndexClient.getShardHttpSolrServer(ConstantUtil.HTTP_SOLR_SERVER_URL);
        }
        
        log.info("solrj queryStr======>"+queryStr);
        SolrParams solrParams = SolrRequestParsers.parseQueryString(queryStr);
        QueryResponse rsp = server.query( solrParams);

        if (rsp != null) {
            return rsp.getResults();
        }
        else{
            log.info("QueryResponse is null");
            
            return null;
        }
    }
    
    /**
     * 请求返回信息封装json对象
     * @param flag  0 请求参数错误 1 请求正常 2 系统异常
     * @count 符合条件的记录总数
     * @param o 提示信息或正常返回结果集
     * @return  错误时返回提示信息，正常返回结果集
     */
    private JSONObject setJsonObjectElement(int flag,String count,Object o){
        JSONObject object = new JSONObject();
        object.put("flag", flag);
        object.put("count", count);
        object.put("result", o);
        
        return object;
    }
}
