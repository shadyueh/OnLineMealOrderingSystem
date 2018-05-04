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

	public void updateDishState(String id, int state) throws SQLException {
		String sql = "update dish set state=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, state, id);

	}

	public int findAllCountByText(String paramkey, String paramvalue) throws SQLException {
		String sql = "select count(*) from dish where category=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler(), paramvalue);
		return (int) count;
	}

	public List<Dish> findPageListByText(int page, int rows, String paramkey, String paramvalue) throws SQLException {
		String sql = "select * from (select * from dish where category=?) as dishbycondtion limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Dish>(Dish.class), paramvalue, (page - 1) * rows, rows);
	}

	public int findAllCountByLike(String paramkey, String paramvalue) throws SQLException {
		String sql = "select count(*) from dish where name like ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler(), "%" + paramvalue + "%");
		return (int) count;

	}

	public List<Dish> findPageListByLike(int page, int rows, String paramkey, String paramvalue) throws SQLException {
		String sql = "select * from(select * from dish where name like ?) as dishbycondtion limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Dish>(Dish.class), "%" + paramvalue + "%", (page - 1) * rows,
				rows);

	}

	public int findAllCountByNum(String paramkey, int paramvalue1) throws SQLException {
		String sql = "select count(*) from dish where state=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler(), paramvalue1);
		return (int) count;
	}

	public List<Dish> findPageListByNum(int page, int rows, String paramkey, int paramvalue1) throws SQLException {
		System.out.println("传入数据库参数----------------------paramkey：：：" + paramkey + ";paramvalue1::::" + paramvalue1);
		String sql = "select * from (select * from dish where state=?) as dishbycondtion limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Dish>(Dish.class), paramvalue1, (page - 1) * rows, rows);
	}

	public int findAllCountByBetweenPrice(String paramkey, int parseInt, int parseInt2) throws SQLException {
		String sql = "select count(*) from dish where price between ? and ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler(), parseInt, parseInt2);
		return (int) count;
	}

	public List<Dish> findPageListByBetweenPrice(int page, int rows, String paramkey, int parseInt, int parseInt2)
			throws SQLException {
		String sql = "select * from(select * from dish where price between ? and ?) as dishbycondtion limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Dish>(Dish.class), parseInt, parseInt2, (page - 1) * rows, rows);
	}

	public int findAllCountByBetweenPnum(String paramkey, int parseInt, int parseInt2) throws SQLException {
		String sql = "select count(*) from dish where pnum between ? and ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		long count = (Long) runner.query(sql, new ScalarHandler(), parseInt, parseInt2);
		return (int) count;
	}

	public List<Dish> findPageListByBetweenPnum(int page, int rows, String paramkey, int parseInt, int parseInt2)
			throws SQLException {
		String sql = "select * from(select * from dish where pnum between ? and ?) as dishbycondtion limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Dish>(Dish.class), parseInt, parseInt2, (page - 1) * rows, rows);
	}

}
