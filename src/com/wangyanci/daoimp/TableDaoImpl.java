package com.wangyanci.daoimp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.wangyanci.dao.TableDao;
import com.wangyanci.pojo.Table;
import com.wangyanci.utils.DataSourceUtils;

public class TableDaoImpl implements TableDao {

	public void addTable(Table t) throws SQLException {
		String sql = "insert into tab(id,name,state,capacity,location,category,imgurl,description) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		runner.update(sql, t.getId(), t.getName(), t.getState(), t.getCapacity(), t.getLocation(), t.getCategory(),
				t.getImgurl(), t.getDescription());

	}

}
