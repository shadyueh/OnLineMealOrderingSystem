package com.wangyanci.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Cart;

public class DeleteAllFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteAllFromCart() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		request.getSession().removeAttribute("cart");
		cart = new Cart();
		request.getSession().setAttribute("cart", cart);
		response.getWriter().write("delete all success");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
