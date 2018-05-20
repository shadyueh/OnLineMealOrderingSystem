package com.wangyanci.dao;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.Orders;

public interface OrderDao {

	void addOrder(Orders order) throws SQLException;

	List<Orders> findNoPayOrders(int uid) throws SQLException;

	int findNoPayOrdersCount(int uid) throws SQLException;

	List<Orders> findNoPayOrdersByPage(int uid, int page, int rows) throws SQLException;

	Orders findOrderById(String orderid) throws SQLException;

	void pay(String orderid) throws SQLException;

	int findPayOrdersCount(int uid) throws SQLException;

	List<Orders> findPayOrdersByPage(int uid, int page, int rows) throws SQLException;

	int getAllOrderCount() throws SQLException;

	List<Orders> getAllOrdersByPage(int page, int rows) throws SQLException;

}
