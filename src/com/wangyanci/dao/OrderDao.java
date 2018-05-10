package com.wangyanci.dao;

import java.sql.SQLException;

import com.wangyanci.pojo.Orders;

public interface OrderDao {

	void addOrder(Orders order) throws SQLException;

}
