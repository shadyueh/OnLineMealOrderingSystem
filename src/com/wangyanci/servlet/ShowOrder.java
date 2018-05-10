package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.Table;
import com.wangyanci.pojo.User;
import com.wangyanci.service.OrderService;
import com.wangyanci.serviceimp.OrderServiceImpl;

public class ShowOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowOrder() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			Map<String, Table> tablemap = cart.getTmap();
			System.out.println("----------------------------------");
			System.out.println("----------------------------------" + tablemap.keySet().size());
			if (tablemap.keySet().size() == 0) {
				request.getRequestDispatcher("/show_table.jsp").forward(request, response);

			} else {
				User user = (User) request.getSession().getAttribute("user");

				OrderService service = new OrderServiceImpl();
				try {
					service.buildOrder(user, cart);
					// cart = new Cart();
					//
					// request.getSession().removeAttribute("cart");
					// request.getSession().setAttribute("cart", cart);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("/show_order.jsp").forward(request, response);

			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
