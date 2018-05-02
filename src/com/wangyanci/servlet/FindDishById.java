package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Dish;
import com.wangyanci.service.DishService;
import com.wangyanci.serviceimp.DishServiceImp;

public class FindDishById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindDishById() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到商品id
		String id = request.getParameter("id");
		String dist = request.getParameter("dist");
		// 2.调用service,dao完成查询商品操作
		DishService service = new DishServiceImp();
		try {
			Dish p = service.findById(id);
			// 将p存储到request域，请求转发到productInfo.jsp页面，展示 商品信息.
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + p);
			request.setAttribute("p", p);
			if (dist.equals("list")) {
				request.getRequestDispatcher("/dishInfo.jsp").forward(request, response);
			} else if (dist.equals("edit")) {
				request.getRequestDispatcher("/editDish.jsp").forward(request, response);
			}
			return;

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
