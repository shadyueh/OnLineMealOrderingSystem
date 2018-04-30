package com.wangyanci.serviceimp;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.wangyanci.dao.UserDao;
import com.wangyanci.daoimp.UserDaoImp;
import com.wangyanci.exception.ActiveCodeException;
import com.wangyanci.exception.LoginException;
import com.wangyanci.exception.RegistException;
import com.wangyanci.pojo.User;
import com.wangyanci.service.UserService;
import com.wangyanci.utils.MailUtils;

public class UserServiceImp implements UserService {

	public void regist(User user) throws RegistException {
		UserDao userdao = new UserDaoImp();

		try {
			userdao.regist(user);
			// 向注册成功的用户发送一封激活邮件。

			String emailMsg = "注册成功，请<a href='http://localhost:8080/OnLineOrderingSystem/active?activeCode="
					+ user.getActivecode() + "'>激活</a>,激活码为:" + user.getActivecode();
			MailUtils.sendMail(user.getEmail(), emailMsg);

		} catch (SQLException e) {
			throw new RegistException("注册失败");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	// 激活用户操作
	public void activeUser(String activeCode) throws SQLException {
		UserDao dao = new UserDaoImp();
		// 1.根据激活码查询用户，要判断激活码是否过期.

		User user = dao.findUserByActiveCode(activeCode);
		if (user != null) {
			// 2.进行激活操作
			long time = System.currentTimeMillis() - user.getRegisttime().getTime();

			if (time <= 24 * 60 * 1000 * 60) {
				// 激活
				dao.activeUser(activeCode);
			} else {
				throw new ActiveCodeException("激活码过期");
			}
		} else {
			throw new ActiveCodeException("用户不存在");
		}
	}

	public User login(String username, String password) throws LoginException {
		UserDao dao = new UserDaoImp();
		try {
			User user = dao.login(username, password);
			if (user != null) {
				// 判断用户是否激活
				if (user.getActivestate() == 1) {
					return user;
				} else {
					throw new ActiveCodeException("用户未激活");
				}
			} else {
				throw new LoginException("用户名或密码错误");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("用户名或密码错误");
		}

	}

}
