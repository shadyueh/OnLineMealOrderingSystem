package com.wangyanci.dao;

import java.sql.SQLException;

import com.wangyanci.pojo.OrderDish;

public interface OrderDishDao {

	void addOrderDish(OrderDish orderDish) throws SQLException;

}
