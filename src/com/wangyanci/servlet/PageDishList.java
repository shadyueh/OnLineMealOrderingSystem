package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.PageDish;
import com.wangyanci.service.DishService;
import com.wangyanci.serviceimp.DishServiceImp;

public class PageDishList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PageDishList() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		String _page = request.getParameter("page");
		if (_page != null) {
			page = Integer.parseInt(_page);
		}
		int rows = 5;

		String _rows = request.getParameter("rows");
		if (_rows != null) {
			rows = Integer.parseInt(_rows);
		}

		DishService service = new DishServiceImp();
		try {
			PageDish pd = service.findPageList(page, rows);
			// 将p存储到request域，请求转发到productInfo.jsp页面，展示 商品信息.
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + pd);
			request.setAttribute("pd", pd);

			request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
			// response.getWriter().write(ps.toString());
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
