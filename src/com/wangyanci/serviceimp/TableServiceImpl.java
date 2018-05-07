package com.wangyanci.serviceimp;

import java.sql.SQLException;

import com.wangyanci.dao.TableDao;
import com.wangyanci.daoimp.TableDaoImpl;
import com.wangyanci.pojo.Table;
import com.wangyanci.service.TableService;

public class TableServiceImpl implements TableService {

	public void addTable(Table t) throws SQLException {
		TableDao dao = new TableDaoImpl();
		dao.addTable(t);
	}

}
