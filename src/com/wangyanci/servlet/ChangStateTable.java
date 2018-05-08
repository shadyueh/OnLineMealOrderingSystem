package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.service.TableService;
import com.wangyanci.serviceimp.TableServiceImpl;

public class ChangStateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangStateTable() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String str = request.getParameter("ids");
		String[] strs = str.split(",");
		int state = Integer.parseInt(request.getParameter("state"));

		TableService service = new TableServiceImpl();

		try {
			for (String string : strs) {
				service.changeStateTable(string, state);
			}
			response.getWriter().write("ok");
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
