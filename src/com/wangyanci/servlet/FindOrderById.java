package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wangyanci.pojo.OrderInfo;
import com.wangyanci.service.OrderService;
import com.wangyanci.serviceimp.OrderServiceImpl;

public class FindOrderById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindOrderById() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("~~~~~~~~~~~~ids~~~~~~~~" + id);

		OrderService service = new OrderServiceImpl();
		try {
			OrderInfo orderInfo = service.findOrderByiD(id);
			request.getSession().setAttribute("orderInfo", orderInfo);
			Gson gson = new Gson();
			String string = gson.toJson(orderInfo);
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^" + string);
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write(string);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
