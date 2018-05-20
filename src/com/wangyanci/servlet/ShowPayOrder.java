package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.NoPayOrdersResult;
import com.wangyanci.pojo.User;
import com.wangyanci.service.OrderService;
import com.wangyanci.serviceimp.OrderServiceImpl;

public class ShowPayOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowPayOrder() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService service = new OrderServiceImpl();
		int page = 1;
		int rows = 2;

		String page1 = request.getParameter("page");
		if (page1 != null) {
			page = Integer.parseInt(page1);
		}
		String rows1 = request.getParameter("rows");
		if (rows1 != null) {
			rows = Integer.parseInt(rows1);
		}

		User user = (User) request.getSession().getAttribute("user");
		int uid = user.getId();
		try {
			NoPayOrdersResult payresult = service.gerPayOrder(uid, page, rows);
			System.out.println(payresult);

			request.setAttribute("payresult", payresult);
			request.getRequestDispatcher("/show_pay_order.jsp").forward(request, response);
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
