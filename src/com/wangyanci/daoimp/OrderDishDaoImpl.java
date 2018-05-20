package com.wangyanci.daoimp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wangyanci.dao.OrderDishDao;
import com.wangyanci.pojo.OrderDish;
import com.wangyanci.utils.DataSourceUtils;

public class OrderDishDaoImpl implements OrderDishDao {

	public void addOrderDish(OrderDish orderDish) throws SQLException {
		String sql = "insert into order_dish(order_id,dish_id,buynum) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, orderDish.getOrder_id(), orderDish.getDish_id(), orderDish.getBuynum());
	}

	public List<OrderDish> findDish(String id) throws SQLException {
		String sql = "select * from order_dish where order_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<OrderDish>(OrderDish.class), id);
	}

}
