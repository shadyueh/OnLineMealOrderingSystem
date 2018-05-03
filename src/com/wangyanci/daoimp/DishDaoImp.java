package com.wangyanci.daoimp;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wangyanci.dao.DishDao;
import com.wangyanci.pojo.Dish;
import com.wangyanci.utils.DataSourceUtils;

public class DishDaoImp implements DishDao {
	public List<Dish> findAll() throws SQLException {
		String sql = "select * from dish";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		return runner.query(sql, new BeanListHandler<Dish>(Dish.class));
	}

	public Dish findById(String id) throws SQLException {
		String sql = "select * from dish where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		return runner.query(sql, new BeanHandler<Dish>(Dish.class), id);
	}

	public void addDish(Dish p) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into dish(id,name,price,pnum,category,imgurl,description,state) values(?,?,?,?,?,?,?,?)";
		System.out.println("8########################################" + p.getPnum());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		runner.update(sql, p.getId(), p.getName(), p.getPrice(), p.getPnum(), p.getCategory(), p.getImgurl(),
				p.getDescription(), p.getState());
	}

	public List<Dish> findPageList(int page, int rows) throws SQLException {
		String sql = "select * from dish limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Dish>(Dish.class), (page - 1) * rows, rows);
	}

	public int findAllCount() throws SQLException {
		String sql = "select count(*) from dish";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler());
		return (int) count;
	}

	public void updateDish(Dish p) throws SQLException {
		String sql = "update dish set name=?,price=?,pnum=?,category=?,imgurl=?,description=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, p.getName(), p.getPrice(), p.getPnum(), p.getCategory(), p.getImgurl(), p.getDescription(),
				p.getId());
	}

	public void deleteById(String id) throws SQLException {
		String sql = "delete from dish where id=?";
		PreparedStatement preparedStatement = DataSourceUtils.getDataSource().getConnection().prepareStatement(sql);
		preparedStatement.setString(1, id);
		int a = preparedStatement.executeUpdate();

		// QueryRunner runner = new
		// QueryRunner(DataSourceUtils.getDataSource());
		// int a = runner.update(sql);
		// // int a = runner.update(sql, id);
		System.out.println("delet success^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^---" + a);
	}
}
