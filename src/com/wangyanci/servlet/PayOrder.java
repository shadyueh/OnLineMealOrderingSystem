package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.OrderInfo;
import com.wangyanci.service.OrderService;
import com.wangyanci.serviceimp.OrderServiceImpl;

public class PayOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PayOrder() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OrderInfo orderInfo = (OrderInfo) request.getSession().getAttribute("orderInfo");
		if (orderInfo != null && orderInfo.getTotal() != 0) {
			request.getSession().removeAttribute("orderInfo");
		}
		orderInfo = new OrderInfo();
		request.getSession().setAttribute("orderInfo", orderInfo);

		String orderid = request.getParameter("orderid");
		OrderService service = new OrderServiceImpl();
		try {
			service.pay(orderid);
			response.getWriter().write("ok");
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
