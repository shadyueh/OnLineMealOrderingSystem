package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.service.UserService;
import com.wangyanci.serviceimp.UserServiceImp;

public class UpUserGrant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpUserGrant() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String str = request.getParameter("ids");
		String[] ids = str.split(",");
		String role = request.getParameter("role");
		UserService service = new UserServiceImp();

		try {
			for (String id : ids) {
				service.changeStateUser(Integer.parseInt(id), role);
				System.out.println("############id#############" + id);
				System.out.println("############role#############" + role);
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
