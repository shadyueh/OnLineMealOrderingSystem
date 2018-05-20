package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.OrderInfo;
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
		OrderInfo orderInfo2 = (OrderInfo) request.getSession().getAttribute("orderInfo");
		if (orderInfo2 == null || orderInfo2.getTotal() == 0) {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			String orderid = null;
			if (cart != null && cart.getMap().keySet().size() != 0) {
				Map<String, Table> tablemap = cart.getTmap();
				System.out.println("----------------------------------");
				System.out.println("----------------------------------" + tablemap.keySet().size());
				if (tablemap.keySet().size() == 0) {
					request.getRequestDispatcher("/show_table.jsp").forward(request, response);

				} else {
					User user = (User) request.getSession().getAttribute("user");
					OrderInfo orderInfo;
					OrderService service = new OrderServiceImpl();
					try {
						orderid = service.buildOrder(user, cart);
						cart = new Cart();

						request.getSession().removeAttribute("cart");
						request.getSession().setAttribute("cart", cart);

						orderInfo = service.findOrderByiD(orderid);

						orderInfo2 = (OrderInfo) request.getSession().getAttribute("orderInfo");
						if (orderInfo2 != null) {
							request.getSession().removeAttribute("orderInfo");
						}
						request.getSession().setAttribute("orderInfo", orderInfo);
						request.getRequestDispatcher("/show_order.jsp").forward(request, response);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else {
				request.getRequestDispatcher("/").forward(request, response);
			}
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
