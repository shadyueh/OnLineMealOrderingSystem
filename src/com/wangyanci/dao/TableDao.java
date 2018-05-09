package com.wangyanci.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.wangyanci.pojo.Table;

public interface TableDao {

	void addTable(Table t) throws SQLException;

	List<Table> getItemList(int page, int rows) throws SQLException;

	int getCount() throws SQLException;

	Table findById(String id) throws SQLException;

	void updateTable(Table tb) throws SQLException;

	void deleteTable(String id) throws SQLException;

	void changeStateTable(String string, int state) throws SQLException;

	List<Table> findTableByCondtion(Table tb, int page, int rows) throws SQLException;

	int getCountBycondtion(Table tb) throws SQLException;

	void sureTable(String id, Date stime, Date etime) throws SQLException;

}
