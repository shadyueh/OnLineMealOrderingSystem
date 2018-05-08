package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wangyanci.pojo.Table;
import com.wangyanci.service.TableService;
import com.wangyanci.serviceimp.TableServiceImpl;

public class FindById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindById() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("_");
		System.out.println("---------------id-----------" + id);
		id = "137a8fb1-b09b-4f5f-acc0-a27b51a2fdfa";
		TableService service = new TableServiceImpl();
		try {
			Table tb = service.findById(id);
			Gson gson = new Gson();
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write(gson.toJson(tb));
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
