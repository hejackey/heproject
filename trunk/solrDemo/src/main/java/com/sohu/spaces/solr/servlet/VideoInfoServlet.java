package com.sohu.spaces.solr.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.response.UpdateResponse;

import com.sohu.spaces.solr.service.VideoInfoIndexService;

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
        VideoInfoIndexService instance = VideoInfoIndexService.getInstantce();
        Long vid = Long.valueOf(request.getParameter("vid"));
        UpdateResponse indexResp =  instance.updateVideoIndex(vid);
        
        System.out.println(indexResp.getStatus());
        System.out.println(indexResp.getQTime());
    }
}
