package com.wangyanci.dao;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.User;

public interface UserDao {
	public void regist(User user) throws SQLException;

	public User login(String username, String password) throws SQLException;

	public User findUserByActiveCode(String activeCode) throws SQLException;

	public void activeUser(String activeCode) throws SQLException;

	public User findUserById(int id) throws SQLException;

	public List<User> getUserList(int page, int rows) throws SQLException;

	public int getCount() throws SQLException;

	public void changeStateUser(int id, String role) throws SQLException;

	public void banUser(int id, int state) throws SQLException;
}
