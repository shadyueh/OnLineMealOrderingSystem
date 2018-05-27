package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.service.UserService;
import com.wangyanci.serviceimp.UserServiceImp;

public class BanUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BanUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String str = request.getParameter("ids");
		String[] ids = str.split(",");
		int state = Integer.parseInt(request.getParameter("state"));
		UserService service = new UserServiceImp();

		try {
			for (String id : ids) {
				service.banUser(Integer.parseInt(id), state);
			}
			response.getWriter().write("ok");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
