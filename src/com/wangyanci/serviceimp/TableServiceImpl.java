package com.wangyanci.serviceimp;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.dao.TableDao;
import com.wangyanci.daoimp.TableDaoImpl;
import com.wangyanci.pojo.Table;
import com.wangyanci.pojo.TablePageListResult;
import com.wangyanci.service.TableService;

public class TableServiceImpl implements TableService {

	public void addTable(Table t) throws SQLException {
		TableDao dao = new TableDaoImpl();
		dao.addTable(t);
	}

	public TablePageListResult getItemList(int page, int rows) throws SQLException {
		TableDao dao = new TableDaoImpl();
		List<Table> list = dao.getItemList(page, rows);
		int count = dao.getCount();
		TablePageListResult result = new TablePageListResult();
		result.setRows(list);
		result.setTotal(count);
		return result;
	}

	public Table findById(String id) throws SQLException {
		TableDao dao = new TableDaoImpl();
		Table tb = dao.findById(id);
		return tb;
	}

	public void updateTable(Table tb) throws SQLException {
		TableDao dao = new TableDaoImpl();
		dao.updateTable(tb);

	}

	public void deleteTable(String id) throws SQLException {
		TableDao dao = new TableDaoImpl();
		dao.deleteTable(id);

	}

	public void changeStateTable(String string, int state) throws SQLException {
		TableDao dao = new TableDaoImpl();
		dao.changeStateTable(string, state);

	}

	public TablePageListResult findTableBycontion(Table tb, int page, int rows) throws SQLException {

		TableDao dao = new TableDaoImpl();
		List<Table> list = dao.findTableByCondtion(tb, page, rows);
		int count = dao.getCountBycondtion(tb);
		TablePageListResult result = new TablePageListResult();
		result.setRows(list);
		result.setTotal(count);
		return result;
	}

}
