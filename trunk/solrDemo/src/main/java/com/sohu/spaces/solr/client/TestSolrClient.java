package com.sohu.spaces.solr.client;


import java.text.ParseException;

import org.apache.commons.httpclient.util.DateParseException;
import com.sohu.spaces.solr.service.VideoInfoIndexService;

public class TestSolrClient {
    public static void main(String[] args) throws DateParseException, ParseException {
        VideoInfoIndexService service = VideoInfoIndexService.getInstantce();
        service.allVideoInfoIndex();
        
        System.exit(0);
    }
}
