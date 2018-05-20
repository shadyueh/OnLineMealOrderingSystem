package com.wangyanci.dao;

import java.sql.SQLException;

import com.wangyanci.pojo.User;

public interface UserDao {
	public void regist(User user) throws SQLException;

	public User login(String username, String password) throws SQLException;

	public User findUserByActiveCode(String activeCode) throws SQLException;

	public void activeUser(String activeCode) throws SQLException;

	public User findUserById(int id) throws SQLException;
}
