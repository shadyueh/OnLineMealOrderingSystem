package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wangyanci.pojo.TablePageListResult;
import com.wangyanci.service.TableService;
import com.wangyanci.serviceimp.TableServiceImpl;

public class ListTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListTable() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));

		TableService service = new TableServiceImpl();

		try {
			TablePageListResult result = service.getItemList(page, rows);
			Gson gson = new Gson();
			String string = gson.toJson(result);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(string);
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
