package com.wangyanci.dao;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.OrderDish;

public interface OrderDishDao {

	void addOrderDish(OrderDish orderDish) throws SQLException;

	List<OrderDish> findDish(String id) throws SQLException;

}
