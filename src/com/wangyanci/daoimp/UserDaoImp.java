package com.wangyanci.daoimp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.wangyanci.dao.UserDao;
import com.wangyanci.pojo.User;
import com.wangyanci.utils.DataSourceUtils;
import com.wangyanci.utils.Md5Utils;

public class UserDaoImp implements UserDao {

	public void regist(User user) throws SQLException {
		// 注册操作
		String sql = "insert into user values(null,?,?,?,?,?,?,?,null)";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		runner.update(sql, user.getUsername(), Md5Utils.md5(user.getPassword()), user.getNickname(), user.getEmail(),
				"user", 0, user.getActivecode());

	}

	public User login(String username, String password) throws SQLException {
		String sql = "select * from user where username=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username, Md5Utils.md5(password));
	}

	public User findUserByActiveCode(String activeCode) throws SQLException {
		String sql = "select * from user where activecode=?";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		System.out.println("ffffffffffffffff");

		return runner.query(sql, new BeanHandler<User>(User.class), activeCode);
	}

	public void activeUser(String activeCode) throws SQLException {
		String sql = "update user set activestate=1 where activecode=?";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		System.out.println("aaaaaa");

		runner.update(sql, activeCode);

	}

	public User findUserById(int id) throws SQLException {
		String sql = "select * from user where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), id);
	}

}
