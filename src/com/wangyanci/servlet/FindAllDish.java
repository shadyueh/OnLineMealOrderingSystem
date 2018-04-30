package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Dish;
import com.wangyanci.service.DishService;
import com.wangyanci.serviceimp.DishServiceImp;

public class FindAllDish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindAllDish() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.查询所有菜肴
		DishService service = new DishServiceImp();
		try {

			List<Dish> ps = service.findAll();
			// 2.将所有菜肴信息存储到request域。

			request.setAttribute("ps", ps);
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
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
