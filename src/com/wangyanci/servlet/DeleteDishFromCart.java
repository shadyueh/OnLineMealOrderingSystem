package com.wangyanci.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Cart;

public class DeleteDishFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteDishFromCart() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.setTotal(cart.getTotal() - cart.getMap().get(id).getCount() * cart.getMap().get(id).getDish().getPrice());
		cart.getMap().remove(id);
		response.getWriter().write("delete success");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
