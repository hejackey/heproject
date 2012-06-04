package com.sohu.spaces.solr.model;

/**
 * 查询信息封装类
 * @author xiaolianghe
 *
 */
public class QueryVideoInfo {
    private String key;                          //查询关键字
    private String uploadUserName;    //上传用户名
    private Long uploadUserId;            //上传用户id
    private String uploadIp;                 //上传ip
    private String sort;                         //排序条件 (1.上传时间 2.最后修改时间 其他默认相关程度)
    private String cateCode;                //分类编号
    private Integer uploadSource;       //上传来源
    private Integer cUploadStatus;      //上传状态
    private Integer cCovertStatus;       //转码状态
    private Integer cPubStatus;            //发布状态
    private Integer cValid;                    //有效状态
    private Integer uStatus;                 //ugc视频状态
    private String cSource;                  //0、全部 1、ugc 2、vrs（查询范围）
    private String isPrecise;                 //是否精准（1、精准 其他非精准）
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getUploadUserName() {
        return uploadUserName;
    }
    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }
    public Long getUploadUserId() {
        return uploadUserId;
    }
    public void setUploadUserId(Long uploadUserId) {
        this.uploadUserId = uploadUserId;
    }
    public String getUploadIp() {
        return uploadIp;
    }
    public void setUploadIp(String uploadIp) {
        this.uploadIp = uploadIp;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getCateCode() {
        return cateCode;
    }
    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }
    public Integer getUploadSource() {
        return uploadSource;
    }
    public void setUploadSource(Integer uploadSource) {
        this.uploadSource = uploadSource;
    }
    public Integer getcUploadStatus() {
        return cUploadStatus;
    }
    public void setcUploadStatus(Integer cUploadStatus) {
        this.cUploadStatus = cUploadStatus;
    }
    public Integer getcCovertStatus() {
        return cCovertStatus;
    }
    public void setcCovertStatus(Integer cCovertStatus) {
        this.cCovertStatus = cCovertStatus;
    }
    public Integer getcPubStatus() {
        return cPubStatus;
    }
    public void setcPubStatus(Integer cPubStatus) {
        this.cPubStatus = cPubStatus;
    }
    public Integer getcValid() {
        return cValid;
    }
    public void setcValid(Integer cValid) {
        this.cValid = cValid;
    }
    public Integer getuStatus() {
        return uStatus;
    }
    public void setuStatus(Integer uStatus) {
        this.uStatus = uStatus;
    }
    public String getcSource() {
        return cSource;
    }
    public void setcSource(String cSource) {
        this.cSource = cSource;
    }
    public String getIsPrecise() {
        return isPrecise;
    }
    public void setIsPrecise(String isPrecise) {
        this.isPrecise = isPrecise;
    }
}
