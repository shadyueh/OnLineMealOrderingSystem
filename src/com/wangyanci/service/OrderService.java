package com.wangyanci.service;

import java.sql.SQLException;

import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.User;

public interface OrderService {

	void buildOrder(User user, Cart cart) throws SQLException;

}
