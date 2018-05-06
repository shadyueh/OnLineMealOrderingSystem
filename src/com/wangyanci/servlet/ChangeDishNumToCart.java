package com.wangyanci.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Cart;

public class ChangeDishNumToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeDishNumToCart() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String id = request.getParameter("id");
		id = id.substring(1);
		String op = request.getParameter("op");
		if (op.equals("add")) {
			cart.getMap().get(id).setCount(cart.getMap().get(id).getCount() + 1);
			cart.setTotal(cart.getTotal() + cart.getMap().get(id).getDish().getPrice());
			System.out.println("~~~~~######cart total#####" + cart.getTotal());

		} else {
			if (cart.getMap().get(id).getCount() <= 1) {
				cart.setTotal(cart.getTotal() - cart.getMap().get(id).getDish().getPrice());
				cart.getMap().remove(id);
				System.out.println("~~~~~######cart total#####" + cart.getTotal());
			} else {
				cart.getMap().get(id).setCount(cart.getMap().get(id).getCount() - 1);
				cart.setTotal(cart.getTotal() - cart.getMap().get(id).getDish().getPrice());
				System.out.println("~~~~~######cart total#####" + cart.getTotal());
			}
		}
		response.getWriter().write("change num success");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
