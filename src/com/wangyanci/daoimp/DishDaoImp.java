package com.wangyanci.daoimp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
		String sql = "insert into dish values(?,?,?,?,?,?,?)";
		System.out.println("8########################################" + p.getPnum());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		runner.update(sql, p.getId(), p.getName(), p.getPrice(), p.getPnum(), p.getCategory(), p.getImgurl(),
				p.getDescription());
	}

}
