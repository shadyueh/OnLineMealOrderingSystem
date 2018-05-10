package com.wangyanci.daoimp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.wangyanci.dao.OrderTableDao;
import com.wangyanci.pojo.OrderTab;
import com.wangyanci.utils.DataSourceUtils;

public class OrderTableDaoImpl implements OrderTableDao {

	public void addOrderTable(OrderTab orderTab) throws SQLException {
		String sql = "insert into order_tab(order_id,tab_id,begintime,endtime) values(?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, orderTab.getOrderId(), orderTab.getTabId(), orderTab.getBegintime(), orderTab.getEndtime());

	}

}
