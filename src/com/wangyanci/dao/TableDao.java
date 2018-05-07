package com.wangyanci.dao;

import java.sql.SQLException;

import com.wangyanci.pojo.Table;

public interface TableDao {

	void addTable(Table t) throws SQLException;

}
