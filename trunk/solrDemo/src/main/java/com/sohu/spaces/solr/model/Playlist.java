package com.sohu.spaces.solr.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Playlist implements Serializable {

	private static final long serialVersionUID = -1663371942807632325L;

	// identifier
	private Long id;

	// creator of this playlist
	private Long userId;

	private String title;

	private long priority;

	private int categoryId;

	private String coverUrl;

	// 0 or 1, 0 is the default
	private int status = 0;

	private String tag;

	private String description;

	private long createTime;

	private long lastModified;

	private int videoCount;
	
	private int createFrom;
	private String createIp;
    private Integer cateCode;
	
	public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Playlist() {
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	}

	public boolean equals(Object o) {
		boolean equals = false;
		if (o != null && Playlist.class.isAssignableFrom((Class<?>) o)) {
			Playlist playlist = (Playlist) o;
			equals = (new EqualsBuilder().append(id, playlist.getId())).isEquals();
		}
		return equals;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Playlist[");
		buffer.append("categoryId = ").append(categoryId);
		buffer.append(",\n coverUrl = ").append(coverUrl);
		buffer.append(",\n createTime = ").append(createTime);
		buffer.append(",\n description = ").append(description);
		buffer.append(",\n id = ").append(id);
		buffer.append(",\n lastModified = ").append(lastModified);
		buffer.append(",\n priority = ").append(priority);
		buffer.append(",\n status = ").append(status);
		buffer.append(",\n tag = ").append(tag);
		buffer.append(",\n title = ").append(title);
		buffer.append(",\n userId = ").append(userId);
		buffer.append(",\n videoCount = ").append(videoCount);
		buffer.append("]");
		return buffer.toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}
	
	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	public int getCreateFrom() {
		return createFrom;
	}

	public void setCreateFrom(int createFrom) {
		this.createFrom = createFrom;
	}
	
	public Integer getCateCode() {
		return cateCode;
	}

	public void setCateCode(Integer cateCode) {
		this.cateCode = cateCode;
	}	
}
