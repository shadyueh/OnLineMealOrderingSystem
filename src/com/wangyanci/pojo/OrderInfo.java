package com.wangyanci.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class OrderInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	String id;
	double total;
	int paystate;
	Date ordertime;
	User user;
	Map<String, Table> tab_map;
	Map<String, CartDish> dishinfo_map;

	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", total=" + total + ", paystate=" + paystate + ", ordertime=" + ordertime
				+ ", user=" + user + ", tab_map=" + tab_map + ", dishinfo_map=" + dishinfo_map + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getPaystate() {
		return paystate;
	}

	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Table> getTab_map() {
		return tab_map;
	}

	public void setTab_map(Map<String, Table> tab_map) {
		this.tab_map = tab_map;
	}

	public Map<String, CartDish> getDishinfo_map() {
		return dishinfo_map;
	}

	public void setDishinfo_map(Map<String, CartDish> dishinfo_map) {
		this.dishinfo_map = dishinfo_map;
	}
}
