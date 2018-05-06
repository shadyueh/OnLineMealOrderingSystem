package com.wangyanci.pojo;

public class CartDish {
	private Dish dish;
	// 数量
	private Integer count;
	// 小计
	private Double subtotal;

	public Dish getDish() {
		return dish;
	}

	@Override
	public String toString() {
		return "CartDish [dish=" + dish + ", count=" + count + ", subtotal=" + subtotal + "]";
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return count * dish.getPrice();
	}

}
