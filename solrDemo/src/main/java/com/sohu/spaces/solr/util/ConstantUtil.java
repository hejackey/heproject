package com.sohu.spaces.solr.util;

public class ConstantUtil {
    public static final int C_VERSION_01 = 1;   //高清
    public static final int C_VERSION_02 = 2;   //普通流畅
    public static final int C_VERSION_03 = 3;   //手机移动版
    public static final int C_VERSION_04 = 4;   //mp4
    public static final int C_VERSION_05 = 5;   //flv
    public static final int C_VERSION_06 = 6;   //超清
    public static final int C_VERSION_07 = 7;   //原画
            
    public static final int UGC_VER_TYPE_1 = 1;    //高清
    public static final int UGC_VER_TYPE_2 = 2;    //流畅
    public static final int UGC_VER_TYPE_21 = 21;   //超清
    public static final int UGC_VER_TYPE_31 = 31;   //原画
    public static final int UGC_VER_TYPE_10 = 10;   //手机
    
    public static final int VIDEO_TYPE_LONG = 1;    //长视频
    public static final int VIDEO_TYPE_SHORT = 0;   //短视频
    
    public static final String VIDEO_SOURCE_UGC = "UGC";   
    public static final String VIDEO_SOURCE_VRS = "VRS";   
    
    public static final int UGC_STATUS_10 = 10;    //视频上传未完成
    public static final int UGC_STATUS_11 = 11;    //上传中断
    public static final int UGC_STATUS_12 = 12;   //上传失败
    public static final int UGC_STATUS_20 = 20;   //截图失败，视频可播放
    public static final int UGC_STATUS_21 = 21;   //正在转码
    public static final int UGC_STATUS_22 = 22;   //转码失败
    public static final int UGC_STATUS_30 = 30;   //转码截图完成，可播放，未审核
    public static final int UGC_STATUS_40 = 40;   //编辑二次审核通过
    public static final int UGC_STATUS_37 = 37;   //监控审核删除
    
    public static final int UPLOAD_STATUS_0 = 0;   //上传失败
    public static final int UPLOAD_STATUS_1 = 1;   //上传成功
    
    public static final int COVERT_STATUS_0 = 0;   //未转码
    public static final int COVERT_STATUS_1 = 1;   //转码完成
    public static final int COVERT_STATUS_2 = 2;   //转码中
    public static final int COVERT_STATUS_3 = 3;   //转码失败
    
    public static final int PUB_STATUS_0 = 0;   //未发布
    public static final int PUB_STATUS_1 = 1;   //已发布
    
    public static final int VALID_STATUS_0 = 0;   //无效
    public static final int VALID_STATUS_1 = 1;   //有效
    
    public static final String SOLR_CONFIG = "solr_config.properties";
    public static final String ALL_INDEX_START_DATE =PropertiesUtil.getValueFromPropertyByKey(SOLR_CONFIG, "ALL_INDEX_START_DATE");
    public static final String ALL_INDEX_END_DATE = PropertiesUtil.getValueFromPropertyByKey(SOLR_CONFIG, "ALL_INDEX_END_DATE");
    public static final String EMBED_SOLR_HOME = PropertiesUtil.getValueFromPropertyByKey(SOLR_CONFIG, "EMBED_SOLR_HOME");
    public static final String EMBED_SOLR_CORE_NAME=PropertiesUtil.getValueFromPropertyByKey(SOLR_CONFIG, "EMBED_SOLR_CORE_NAME");
    public static final String HTTP_SOLR_SERVER_URL =PropertiesUtil.getValueFromPropertyByKey(SOLR_CONFIG, "HTTP_SOLR_SERVER_URL");
    public static final int INDEX_PAGE_SIZE=Integer.valueOf(PropertiesUtil.getValueFromPropertyByKey(SOLR_CONFIG, "INDEX_PAGE_SIZE"));
    public static final int JVM_CREATE_INDEX = Integer.valueOf(PropertiesUtil.getValueFromPropertyByKey(ConstantUtil.SOLR_CONFIG, "JVM_CREATE_INDEX"));
}
