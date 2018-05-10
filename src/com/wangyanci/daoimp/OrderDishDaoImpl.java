package com.wangyanci.daoimp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.wangyanci.dao.OrderDishDao;
import com.wangyanci.pojo.OrderDish;
import com.wangyanci.utils.DataSourceUtils;

public class OrderDishDaoImpl implements OrderDishDao {

	public void addOrderDish(OrderDish orderDish) throws SQLException {
		String sql = "insert into order_dish(order_id,dish_id,buynum) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, orderDish.getOrderId(), orderDish.getDishId(), orderDish.getBuynum());
	}

}
