package com.wangyanci.service;

import java.sql.SQLException;
import java.util.Date;

import com.wangyanci.pojo.Table;
import com.wangyanci.pojo.TablePageListResult;

public interface TableService {

	void addTable(Table t) throws SQLException;

	TablePageListResult getItemList(int page, int rows) throws SQLException;

	Table findById(String id) throws SQLException;

	void updateTable(Table tb) throws SQLException;

	void deleteTable(String id) throws SQLException;

	void changeStateTable(String string, int state) throws SQLException;

	TablePageListResult findTableBycontion(Table tb, int page, int rows) throws SQLException;

	void sureTable(String[] strs, Date stime, Date etime) throws SQLException;

}
