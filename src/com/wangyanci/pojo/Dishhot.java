package com.wangyanci.pojo;

import java.util.Date;

public class Dishhot {
	private String dishid;

	private Integer num;

	private Date lastbuytime;

	public String getDishid() {
		return dishid;
	}

	public void setDishid(String dishid) {
		this.dishid = dishid == null ? null : dishid.trim();
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getLastbuytime() {
		return lastbuytime;
	}

	public void setLastbuytime(Date lastbuytime) {
		this.lastbuytime = lastbuytime;
	}

}