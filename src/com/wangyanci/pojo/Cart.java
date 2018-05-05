package com.wangyanci.pojo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	// 购物车存放多个购物项:
	// Map集合用商品的ID作为Map的key , 购物项作为Map的value
	private Map<String, CartDish> map = new HashMap<String, CartDish>();
	// 总计:
	private Double total;

	// 提供三个方法:
	// 将购物项添加到购物车:
	public void addCart(CartDish cartDish) {
		// 获得购物项标识id
		String pid = cartDish.getDish().getId();
		if (map.containsKey(pid)) {
			// 购物车中已经有购物项
			// 购物车中已经有的购物项信息
			CartDish _cartDish = map.get(pid);
			_cartDish.setCount(_cartDish.getCount() + cartDish.getCount());
		} else {
			// 购物车中不存在该购物项
			map.put(pid, cartDish);
		}
		// 总计:
		total += cartDish.getSubtotal();
	}

	// 将购物项从购物车中移除:
	public void removeCart(String pid) {
		// 将购物项从map集合中移除:
		CartDish cartDish = map.remove(pid);
		// 设置总计钱数
		total -= cartDish.getSubtotal();
	}

	// 清空购物车:
	public void clearCart() {
		// 清空map
		map.clear();
		// 总计设置为0
		total = 0d;
	}
}
