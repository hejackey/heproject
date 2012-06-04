package com.sohu.spaces.solr.servlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.mysql.jdbc.StringUtils;

import com.sohu.spaces.solr.client.SolrIndexClient;
import com.sohu.spaces.solr.model.QueryVideoInfo;
import com.sohu.spaces.solr.util.ConstantUtil;
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
    
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        doGet(request,response);
    }
    
    public void doGet(HttpServletRequest request,HttpServletResponse response){
        QueryVideoInfo qVideoInfo = new QueryVideoInfo();
        qVideoInfo = putDataToQueryVideoInfo(request,qVideoInfo);
        
        SolrQuery query = new SolrQuery();
        if ("1".equals(qVideoInfo.getIsPrecise()) && !StringUtils.isNullOrEmpty(qVideoInfo.getKey())) {
            query.setQuery( "c_name_exact:" +qVideoInfo.getKey());
            query.setQuery( "u_tag_exact:" +qVideoInfo.getKey() );
            query.setQuery( "u_introduction_exact:" +qVideoInfo.getKey());
        } else if (!"1".equals(qVideoInfo.getIsPrecise()) ) {
            query.setQuery( "c_name:" +qVideoInfo.getKey());
            query.setQuery( "u_tag:" +qVideoInfo.getKey() );
            query.setQuery( "u_introduction:" +qVideoInfo.getKey());
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
        
        if ("1".equals(qVideoInfo.getSort())) {
            query.addSortField( "c_uploadtime", SolrQuery.ORDER.desc );
        } else if ("2".equals(qVideoInfo.getSort())) {
            query.addSortField( "c_lastmodified", SolrQuery.ORDER.desc );
        }
        
        //设置分页条件
        query.setStart(qVideoInfo.getStart());
        query.setRows(qVideoInfo.getLimit());
        
        SolrServer server = SolrIndexClient.getHttpSolrServer(ConstantUtil.HTTP_SOLR_SERVER_URL);
       
        QueryResponse rsp;
        try {
            rsp = server.query( query );
            SolrDocumentList docs = rsp.getResults();
            System.out.println(docs.size());
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        
    }
    
    private QueryVideoInfo putDataToQueryVideoInfo(HttpServletRequest request,QueryVideoInfo qVideoInfo){
      
        qVideoInfo.setKey(request.getParameter("key"));
        qVideoInfo.setCateCode(request.getParameter("cateCode"));
        
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
        
        qVideoInfo.setcSource(request.getParameter("cSource"));
        
        String cUploadStatus = request.getParameter("cUploadStatus");
        if (!StringUtils.isNullOrEmpty(cUploadStatus)) {
            qVideoInfo.setcUploadStatus(Integer.valueOf(cUploadStatus));
        }
        
        qVideoInfo.setIsPrecise(request.getParameter("isPrecise"));
        qVideoInfo.setSort(request.getParameter("sort"));
        qVideoInfo.setUploadIp(request.getParameter("uploadIp"));
        
        String uploadSource = request.getParameter("uploadSource");
        if (!StringUtils.isNullOrEmpty(uploadSource)) {
            qVideoInfo.setUploadSource(Integer.valueOf(uploadSource));
        }
        
        qVideoInfo.setUploadUserName(request.getParameter("uploadUserName"));
        
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
        
        return qVideoInfo;
    }
}
