package com.wangyanci.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.CartTable;

public class ShowOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowOrder() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Map<String, CartTable> tablemap = cart.getTmap();
		System.out.println("----------------------------------");
		if (tablemap.keySet().size() == 0) {
			request.getRequestDispatcher("/show_table.jsp").forward(request, response);

		} else {

			request.getRequestDispatcher("/show_order.jsp").forward(request, response);

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
