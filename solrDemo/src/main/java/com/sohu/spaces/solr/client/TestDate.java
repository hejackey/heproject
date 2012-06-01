package com.sohu.spaces.solr.client;

import java.text.ParseException;
import java.util.Date;

import com.sohu.spaces.solr.util.DateUtil;


public class TestDate {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Date date = new Date();
        String startTime = DateUtil.formatDate2Str(date,"yyyy-MM-dd");
        String endTime = DateUtil.formatDate2Str(DateUtil.addMinDay(date, -1),"yyyy-MM-dd");
        
        System.out.println(startTime);
        System.out.println(endTime);
        
        System.out.println(DateUtil.compDate(startTime, endTime));
        
       
        
        try {
            Date date2 = DateUtil.formatStr2Date("2007-06-23", "yyyy-MM-dd");
            
            while (true) {
                date = DateUtil.addMinDay(date, -1);
                
               if ( DateUtil.compDateTime(date, date2) ){
                   break;
               }
               
               System.out.println(DateUtil.formatDate2Str(date, "yyyy-MM-dd"));
               
            }
           
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
