package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wangyanci.exception.ActiveCodeException;
import com.wangyanci.service.UserService;
import com.wangyanci.service.UserServiceImp;

public class ActiveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(ActiveUserServlet.class);

	public ActiveUserServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ActiveCodeException {

		// 1.得到激活码
		String activeCode = request.getParameter("activeCode");
		logger.info("激活码————————" + activeCode);
		// 2.调用service中激活操作
		UserService service = new UserServiceImp();

		try {
			service.activeUser(activeCode);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("激活成功，请回<a href='http://localhost:8081/OnLineOrderingSystem/home.jsp'>首页</a>");
			return;

		} catch (SQLException e) {
			// e.printStackTrace();
			// response.sendRedirect(request.getContextPath()
			// + "/error/activeuser_error.jsp");
			// return;
			throw new ActiveCodeException("数据库失败！");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
