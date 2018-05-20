package com.wangyanci.pojo;

import java.util.Date;

public class OrderTab {

	@Override
	public String toString() {
		return "OrderTab [order_id=" + order_id + ", tab_id=" + tab_id + ", begintime=" + begintime + ", endtime="
				+ endtime + "]";
	}

	private String order_id;

	private String tab_id;

	private Date begintime;

	private Date endtime;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getTab_id() {
		return tab_id;
	}

	public void setTab_id(String tab_id) {
		this.tab_id = tab_id;
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

}