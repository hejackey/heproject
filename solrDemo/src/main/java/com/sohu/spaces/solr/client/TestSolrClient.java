package com.sohu.spaces.solr.client;


import java.text.ParseException;

import org.apache.commons.httpclient.util.DateParseException;
import com.sohu.spaces.solr.service.VideoInfoIndexService;

public class TestSolrClient {
    public static void main(String[] args) throws DateParseException, ParseException {
        VideoInfoIndexService service = VideoInfoIndexService.getInstantce();
        service.allVideoInfoIndex();//.allVideoInfoIndexById();//.allVideoInfoIndex();
        /* long time1 = System.currentTimeMillis();
        
        service.updateVideoIndex(66l);
        long time2 = System.currentTimeMillis();
        System.out.println("update video info use time=====>"+(time2-time1)/1000);*/
         //service.updateVideoIndex(65l);
        // service.delVideoIndex(65l);
        
        System.exit(0);
    }
}
