package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.service.DishService;
import com.wangyanci.serviceimp.DishServiceImp;
import com.wangyanci.utils.GetAjaxParmStrUtils;

public class DeleteDishById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteDishById() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramString = GetAjaxParmStrUtils.getStringFromStream(request);
		System.out.println("@@@@@@@@@@@@@@@@@@" + paramString);
		String id = paramString.substring(paramString.lastIndexOf("="));
		System.out.println("#################id in request in deletedash" + id + request.getParameterNames());
		DishService service = new DishServiceImp();
		try {
			service.deleteById(id);
			response.getWriter().write("delete success");

		} catch (SQLException e) {
			response.setStatus(500);
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
