package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.service.DishService;
import com.wangyanci.serviceimp.DishServiceImp;

public class DeleteDishById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteDishById() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		DishService service = new DishServiceImp();

		try {
			service.deleteById(id);
			response.getWriter().write("delete success");

		} catch (

		SQLException e) {
			response.setStatus(500);
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public String toBinary(String str) {

		char[] strChar = str.toCharArray();
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			result += Integer.toBinaryString(strChar[i]) + " ";
		}
		return result;
	}
}
