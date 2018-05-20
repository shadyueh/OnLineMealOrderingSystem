package com.wangyanci.service;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.NoPayOrdersResult;
import com.wangyanci.pojo.OrderInfo;
import com.wangyanci.pojo.TablePageListResult;
import com.wangyanci.pojo.User;

public interface OrderService {

	String buildOrder(User user, Cart cart) throws SQLException;

	List<OrderInfo> gerNoPayOrder(int uid) throws SQLException;

	NoPayOrdersResult gerNoPayOrder(int uid, int page, int rows) throws SQLException;

	OrderInfo findOrderByiD(String orderid) throws SQLException;

	void pay(String orderid) throws SQLException;

	NoPayOrdersResult gerPayOrder(int uid, int page, int rows) throws SQLException;

	TablePageListResult gerAllOrder(int page, int rows) throws SQLException;

}
