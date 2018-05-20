package com.wangyanci.dao;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.OrderTab;

public interface OrderTableDao {

	void addOrderTable(OrderTab orderTable) throws SQLException;

	List<OrderTab> findTable(String id) throws SQLException;

}
