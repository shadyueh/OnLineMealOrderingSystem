package com.wangyanci.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.exception.ActiveCodeException;
import com.wangyanci.exception.LoginException;
import com.wangyanci.pojo.User;
import com.wangyanci.service.UserService;
import com.wangyanci.serviceimp.UserServiceImp;

public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginUserServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.得到请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 2.校验----作业

		// 3.调用service中登录的方法
		UserService service = new UserServiceImp();
		try {
			User user = service.login(username, password);

			// 登录成功

			// 判断是否勾选了记住用户名.
			String remember = request.getParameter("remember");
			if ("on".equals(remember)) {
				// 勾选了--考虑中文问题
				Cookie cookie = new Cookie("remember", URLEncoder.encode(user.getUsername(), "utf-8"));
				cookie.setMaxAge(10 * 24 * 60 * 60);
				cookie.setPath("/");
				response.addCookie(cookie);
			} else {
				// 如果用户没有勾选记住用户名，将cookie删除。删除cookie，只需要设置maxage=0或-1,注意：要与cookie的path一致.
				Cookie cookie = new Cookie("remember", URLEncoder.encode(user.getUsername(), "utf-8"));
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}

			// 自动登录

			String autologin = request.getParameter("autologin");
			if ("on".equals(autologin)) {
				Cookie cookie = new Cookie("autologin",
						URLEncoder.encode(user.getUsername(), "utf-8") + "::" + password);
				cookie.setMaxAge(10 * 24 * 60 * 60);
				cookie.setPath("/");
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("autologin",
						URLEncoder.encode(user.getUsername(), "utf-8") + "::" + password);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}

			request.getSession().invalidate();// 先销毁session。

			request.getSession().setAttribute("user", user);// 登录成功，将user存储到session中.

			response.sendRedirect("http://www.estore.com"); // 重定向可以跳转到任意路径,请求转发只能在本站内跳转.
			return;

		} catch (LoginException e) {
			e.printStackTrace();
			request.setAttribute("login.message", e.getMessage());
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		} catch (ActiveCodeException e) {
			e.printStackTrace();
			request.setAttribute("login.message", e.getMessage());
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
