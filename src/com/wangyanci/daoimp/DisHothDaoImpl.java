package com.wangyanci.daoimp;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wangyanci.dao.DishHotDao;
import com.wangyanci.pojo.Dishhot;
import com.wangyanci.utils.DataSourceUtils;
import com.wangyanci.utils.DateUtils;

public class DisHothDaoImpl implements DishHotDao {

	public void addDishHot(Dishhot dishhot) throws SQLException {
		String sql = "insert into dishhot(dishid,num) values(?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, dishhot.getDishid(), dishhot.getNum());

	}

	public Dishhot getDishHotByDishId(String dishid) throws SQLException {
		String sql = "select * from dishhot where dishid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		return runner.query(sql, new BeanHandler<Dishhot>(Dishhot.class), dishid);
	}

	public void updateDishHotNum(Dishhot dishhot) throws SQLException {
		String sql = "update dishhot set num=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, dishhot.getNum());

	}

	public List<Dishhot> findHot() throws SQLException {
		Date date = DateUtils.GetDate(-30, "days");

		String sql = "select * from (select * from dishhot where lastbuytime > ? order by num desc) temp limit 0,10";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Dishhot>(Dishhot.class), date);
	}

}
