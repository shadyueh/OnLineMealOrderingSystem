package com.wangyanci.daoimp;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

	public List<Table> getItemList(int page, int rows) throws SQLException {
		String sql = "select id,name,begintime,endtime,state,capacity,location,category,imgurl,description,updatetime from  tab limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Table>(Table.class), (page - 1) * rows, rows);
	}

	public int getCount() throws SQLException {
		String sql = "select count(*) from tab";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler());
		return (int) count;
	}

	public Table findById(String id) throws SQLException {
		String sql = "select * from tab where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		return runner.query(sql, new BeanHandler<Table>(Table.class), id);

	}

	public void updateTable(Table tb) throws SQLException {
		String sql = "update tab set category=?,name=?,capacity=?,location=?,imgurl=?,description=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, tb.getCategory(), tb.getName(), tb.getCapacity(), tb.getLocation(), tb.getImgurl(),
				tb.getDescription(), tb.getId());
	}

	public void deleteTable(String id) throws SQLException {
		String sql = "delete from tab where id=?";
		PreparedStatement preparedStatement = DataSourceUtils.getDataSource().getConnection().prepareStatement(sql);
		preparedStatement.setString(1, id);
		int a = preparedStatement.executeUpdate();

		System.out.println("delet success^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^---" + a);
	}

	public void changeStateTable(String string, int state) throws SQLException {
		String sql = "update  tab set state=? where id=?";
		PreparedStatement preparedStatement = DataSourceUtils.getDataSource().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, state);
		preparedStatement.setString(2, string);
		int a = preparedStatement.executeUpdate();

		System.out.println("update success^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^---" + a);
	}

	public List<Table> findTableByCondtion(Table tb, int page, int rows) throws SQLException {
		String string = "select * from tab where state =1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (tb == null) {
			System.out.println("***********tb.getBeginTime" + tb.getBegintime().toString());
			System.out.println("***********tb.getEndTime" + tb.getEndtime().toString());
			if (tb.getBegintime().toString() != "" && tb.getEndtime().toString() != "") {

				string += " and (begintime<? or endtime>?";
				paramList.add(tb.getBegintime());
				paramList.add(tb.getEndtime());

			} else {
				string += " and begintime is null and endtime is null";
			}
			if (tb.getCapacity() != null) {
				string += " and capacity=?";
				paramList.add(tb.getCapacity());
			}
			if (tb.getCategory() != null) {
				string += " and capacity=?";
				paramList.add(tb.getCategory());
			}
			if (tb.getLocation() != null) {
				string += " and location=?";
				paramList.add(tb.getLocation());
			}
			if (tb.getName() != null) {
				string += " and name=?";
				paramList.add(tb.getName());
			}

			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(string, paramList, new BeanListHandler<Table>(Table.class));
		} else {
			string += " and begintime is null and endtime is null";

			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(string, new BeanListHandler<Table>(Table.class));
		}

	}

	public int getCountBycondtion(Table tb) throws SQLException {
		String string = "select count(*) from tab where state =1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (tb == null) {
			System.out.println("***********tb.getBeginTime" + tb.getBegintime().toString());
			System.out.println("***********tb.getEndTime" + tb.getEndtime().toString());
			if (tb.getBegintime().toString() != "" && tb.getEndtime().toString() != "") {

				string += " and (begintime<? or endtime>?";
				paramList.add(tb.getBegintime());
				paramList.add(tb.getEndtime());

			} else {
				string += " and begintime is null and endtime is null";
			}

			if (tb.getCapacity() != null) {
				string += " and capacity=?";
				paramList.add(tb.getCapacity());
			}
			if (tb.getCategory() != null) {
				string += " and capacity=?";
				paramList.add(tb.getCategory());
			}
			if (tb.getLocation() != null) {
				string += " and location=?";
				paramList.add(tb.getLocation());
			}
			if (tb.getName() != null) {
				string += " and name=?";
				paramList.add(tb.getName());
			}

			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			@SuppressWarnings("deprecation")
			long count = (Long) runner.query(string, paramList, new ScalarHandler());
			return (int) count;
		} else {
			string += " and begintime is null and endtime is null";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			@SuppressWarnings("deprecation")
			long count = (Long) runner.query(string, new ScalarHandler());
			return (int) count;
		}

	}

	public void sureTable(String id, Date stime, Date etime) throws SQLException {

		String sql = "update tab set state=3,begintime=?,endtime=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, stime, etime, id);
	}

}
