package com.wangyanci.dao;

import java.sql.SQLException;

import com.wangyanci.pojo.OrderTab;

public interface OrderTableDao {

	void addOrderTable(OrderTab orderTable) throws SQLException;

}
