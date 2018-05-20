package com.wangyanci.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Table {
	@Override
	public String toString() {
		return "Table [id=" + id + ", name=" + name + ", begintime=" + begintime + ", endtime=" + endtime + ", state="
				+ state + ", capacity=" + capacity + ", location=" + location + ", category=" + category + ", imgurl="
				+ imgurl + ", description=" + description + ", updatetime=" + updatetime + "]";
	}

	private String id;

	private String name;

	private Date begintime;

	private Date endtime;

	private Integer state;

	private Integer capacity;

	private String location;

	private String category;

	private String imgurl;

	private String description;
	private Timestamp updatetime;

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl == null ? null : imgurl.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}