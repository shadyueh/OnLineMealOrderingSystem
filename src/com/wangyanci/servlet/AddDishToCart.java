package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.CartDish;
import com.wangyanci.pojo.Dish;
import com.wangyanci.service.DishService;
import com.wangyanci.serviceimp.DishServiceImp;

public class AddDishToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddDishToCart() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		float totalprice = Float.parseFloat(request.getParameter("num"));

		DishService service = new DishServiceImp();

		try {
			Dish dish = service.findById(id);
			System.out.println("--------------dish-------" + dish);
			CartDish cartDish = new CartDish();
			int num = (int) (totalprice / dish.getPrice());
			cartDish.setDish(dish);
			cartDish.setCount(num);
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
				request.getSession().setAttribute("cart", cart);
			}
			System.out.println("--------------cart-------" + cart);
			System.out.println("--------------cartdish-------" + cartDish);
			cart.addCart(cartDish);
			response.getWriter().write("ok");

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
