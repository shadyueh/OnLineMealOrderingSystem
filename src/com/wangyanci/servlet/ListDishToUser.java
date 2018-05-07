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

public class ListDishToUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListDishToUser() {
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
		int rows = 32;

		String _rows = request.getParameter("rows");
		if (_rows != null) {
			rows = Integer.parseInt(_rows);
		}
		String paramkey = request.getParameter("paramkey");

		DishService service;
		System.out.println("paramkey-------------" + paramkey + "--------------------");
		System.out.println("********paramkey != null && paramkey != 'null'-----" + paramkey != null
				&& paramkey != "null" + "--------------------");

		if (paramkey != null && !paramkey.equals("null")) {

			System.out.println("~~~~~~~~~~~~~~~~~~~~不应该进来~~~~~~~~~~~~~~");
			String paramvalue = request.getParameter("paramvalue");
			paramvalue = new String(paramvalue.getBytes("ISO-8859-1"), "UTF-8");
			service = new DishServiceImp();
			try {

				PageDish pd = service.findPageListByCondtion(page, rows, paramkey, paramvalue);
				System.out.println(pd);
				request.setAttribute("pd", pd);

				request.getRequestDispatcher("/listdishtouser.jsp").forward(request, response);
				return;

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			service = new DishServiceImp();
			try {
				System.out.println("~~~~~~~~~~~~~~~~~~~~应该进来~~~~~~~~~~~~~~");
				PageDish pd = service.findPageList(page, rows);
				// 将p存储到request域，请求转发到productInfo.jsp页面，展示 商品信息.
				System.out.println(pd);
				request.setAttribute("pd", pd);

				request.getRequestDispatcher("/listdishtouser.jsp").forward(request, response);
				// response.getWriter().write(ps.toString());
				return;

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
