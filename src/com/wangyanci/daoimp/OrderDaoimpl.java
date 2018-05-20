package com.wangyanci.daoimp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wangyanci.dao.OrderDao;
import com.wangyanci.pojo.Orders;
import com.wangyanci.utils.DataSourceUtils;

public class OrderDaoimpl implements OrderDao {

	public void addOrder(Orders order) throws SQLException {
		String sql = "insert into orders(id,money,paystate,ordertime,user_id) values(?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, order.getId(), order.getMoney(), order.getPaystate(), order.getOrdertime(),
				order.getUser_id());

	}

	public List<Orders> findNoPayOrders(int uid) throws SQLException {
		String sql = "select * from orders where user_id=? and paystate=0";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Orders>(Orders.class), uid);
	}

	public int findNoPayOrdersCount(int uid) throws SQLException {
		String sql = "select count(*) from orders where user_id=? and paystate=0";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler(), uid);
		return (int) count;
	}

	public List<Orders> findNoPayOrdersByPage(int uid, int page, int rows) throws SQLException {
		String sql = "select * from orders where user_id=? and paystate=0 limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Orders>(Orders.class), uid, (page - 1) * rows, rows);
	}

	public Orders findOrderById(String orderid) throws SQLException {
		String sql = "select * from orders where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Orders>(Orders.class), orderid);
	}

	public void pay(String orderid) throws SQLException {
		String sql = "update orders set paystate=1 where id=?";
		System.out.println("+++++++++++++++++++++++++" + orderid);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, orderid);
	}

	public int findPayOrdersCount(int uid) throws SQLException {
		String sql = "select count(*) from orders where user_id=? and paystate=1";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler(), uid);
		return (int) count;
	}

	public List<Orders> findPayOrdersByPage(int uid, int page, int rows) throws SQLException {
		String sql = "select * from orders where user_id=? and paystate=1 limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Orders>(Orders.class), uid, (page - 1) * rows, rows);
	}

	public int getAllOrderCount() throws SQLException {
		String sql = "select count(*) from orders";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler());
		return (int) count;
	}

	public List<Orders> getAllOrdersByPage(int page, int rows) throws SQLException {
		String sql = "select * from orders limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Orders>(Orders.class), (page - 1) * rows, rows);
	}

}
