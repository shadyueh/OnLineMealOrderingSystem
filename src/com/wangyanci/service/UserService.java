package com.wangyanci.service;

import java.sql.SQLException;

import com.wangyanci.exception.LoginException;
import com.wangyanci.exception.RegistException;
import com.wangyanci.pojo.TablePageListResult;
import com.wangyanci.pojo.User;

public interface UserService {
	public void regist(User user) throws RegistException;

	public User login(String username, String password) throws LoginException;

	public void activeUser(String activeCode) throws SQLException;

	public TablePageListResult gerAllUser(int page, int rows) throws SQLException;

	public void changeStateUser(int id, String role) throws SQLException;

	public void banUser(int id, int state) throws SQLException;
}
