package com.wangyanci.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.OrderInfo;

public class PayLatter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PayLatter() {
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
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
