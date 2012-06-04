package com.sohu.spaces.solr.model;

import java.io.Serializable;

/**
 * 数据库查询返回封装类
 * @author xiaolianghe
 *
 */
public class VideoInfo implements Serializable {
	private static final long serialVersionUID = 8865686285495110431L;

	public VideoInfo() {
	}
	
	private Long id;
	private String title;
	private String tag;
	private Long userId;
	private Integer categoriesId;
	private String introduction;
	private Integer status;
	private Long videoLength;
	private Long videoSize;
	private Long uploadTime;
	private String uploadIp;
	private String cusCoverURL;
	private String cutCoverURL;
	private Long lastModified;
	private Integer cateCode;
	/**
	 * 上传来源
	 */
	private Integer uploadFrom;

	private Integer coverIndex;
	/**
	 * 隐私级别
	 */
	private Integer plevel;
	/**
	 * 清晰度版本类�?高清1 超清21 流畅2 原画31 手机10
	 */
	private Integer verType;
	/**
	 * 播放限制 0 无限�?1仅限站内播放 2仅限站外播放 3根据play_limit表限�?	 */
	private Integer playLimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCutCoverURL() {
		return cutCoverURL;
	}

	public void setCutCoverURL(String cutCoverURL) {
		this.cutCoverURL = cutCoverURL;
	}

	public Integer getCategoriesId() {
		return categoriesId;
	}

	public void setCategoriesId(Integer categoriesId) {
		this.categoriesId = categoriesId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getVideoLength() {
		return videoLength;
	}

	public void setVideoLength(Long videoLength) {
		this.videoLength = videoLength;
	}

	public Long getVideoSize() {
		return videoSize;
	}

	public void setVideoSize(Long videoSize) {
		this.videoSize = videoSize;
	}

	public Long getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadIp() {
		return uploadIp;
	}

	public void setUploadIp(String uploadIp) {
		this.uploadIp = uploadIp;
	}

	public String getCusCoverURL() {
		return cusCoverURL;
	}

	public void setCusCoverURL(String cusCoverURL) {
		this.cusCoverURL = cusCoverURL;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}
	
	public Integer getUploadFrom() {
		return uploadFrom;
	}

	public void setUploadFrom(Integer uploadFrom) {
		this.uploadFrom = uploadFrom;
	}
	
	public Integer getCoverIndex() {
		return coverIndex;
	}

	public void setCoverIndex(Integer coverIndex) {
		this.coverIndex = coverIndex;
	}

	public Integer getPlevel() {
		return plevel;
	}

	public void setPlevel(Integer plevel) {
		this.plevel = plevel;
	}

	public Integer getPlayLimit() {
		return playLimit;
	}

	public void setPlayLimit(Integer playLimit) {
		this.playLimit = playLimit;
	}

	public Integer getVerType() {
		return verType;
	}

	public void setVerType(Integer verType) {
		this.verType = verType;
	}
	
	public Integer getCateCode() {
		return cateCode;
	}

	public void setCateCode(Integer cateCode) {
		this.cateCode = cateCode;
	}	

}