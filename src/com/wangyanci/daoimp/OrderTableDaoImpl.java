package com.wangyanci.daoimp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wangyanci.dao.OrderTableDao;
import com.wangyanci.pojo.OrderTab;
import com.wangyanci.utils.DataSourceUtils;

public class OrderTableDaoImpl implements OrderTableDao {

	public void addOrderTable(OrderTab orderTab) throws SQLException {
		String sql = "insert into order_tab(order_id,tab_id,begintime,endtime) values(?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, orderTab.getOrder_id(), orderTab.getTab_id(), orderTab.getBegintime(),
				orderTab.getEndtime());

	}

	public List<OrderTab> findTable(String id) throws SQLException {
		String sql = "select * from order_tab where order_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<OrderTab>(OrderTab.class), id);
	}

}
