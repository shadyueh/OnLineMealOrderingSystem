package com.wangyanci.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.wangyanci.pojo.Table;
import com.wangyanci.pojo.TablePageListResult;
import com.wangyanci.service.TableService;
import com.wangyanci.serviceimp.TableServiceImpl;

public class ListTableToUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListTableToUser() {
		super();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		// map.remove("page");
		// map.remove("rows");
		Table tb = new Table();
		TableService service;
		try {
			BeanUtils.populate(tb, map);
			// tb.setBegintime(new Date(2017, 12, 12, 12, 13, 15));
			// tb.setEndtime(new Date(2019, 1, 1, 1, 1, 1));
			service = new TableServiceImpl();
			TablePageListResult result = service.findTableBycontion(tb, page, rows);
			Gson gson = new Gson();
			String string = gson.toJson(result);
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^" + string);
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write(string);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
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
