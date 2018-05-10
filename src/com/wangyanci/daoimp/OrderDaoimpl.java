package com.wangyanci.daoimp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.wangyanci.dao.OrderDao;
import com.wangyanci.pojo.Orders;
import com.wangyanci.utils.DataSourceUtils;

public class OrderDaoimpl implements OrderDao {

	public void addOrder(Orders order) throws SQLException {
		String sql = "insert into orders(id,money,paystate,ordertime,user_id) values(?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, order.getId(), order.getMoney(), order.getPaystate(), order.getOrdertime(),
				order.getUserId());

	}

}
