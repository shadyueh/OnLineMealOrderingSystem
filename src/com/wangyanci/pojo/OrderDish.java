package com.wangyanci.pojo;

public class OrderDish {
	private String order_id;

	private String dish_id;

	private Integer buynum;

	public Integer getBuynum() {
		return buynum;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getDish_id() {
		return dish_id;
	}

	public void setDish_id(String dish_id) {
		this.dish_id = dish_id;
	}

	public void setBuynum(Integer buynum) {
		this.buynum = buynum;
	}

	@Override
	public String toString() {
		return "OrderDish [order_id=" + order_id + ", dish_id=" + dish_id + ", buynum=" + buynum + "]";
	}

}