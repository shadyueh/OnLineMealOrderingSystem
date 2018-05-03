package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.service.DishService;
import com.wangyanci.serviceimp.DishServiceImp;

public class UpdateDishState extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateDishState() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int state = Integer.parseInt(request.getParameter("state"));
		DishService service = new DishServiceImp();

		try {
			service.updateDishState(id, state);
			response.getWriter().write("update success");

		} catch (SQLException e) {
			response.setStatus(500);
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
