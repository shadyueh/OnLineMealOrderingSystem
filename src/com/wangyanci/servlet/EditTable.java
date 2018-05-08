package com.wangyanci.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.wangyanci.pojo.Table;
import com.wangyanci.service.TableService;
import com.wangyanci.serviceimp.TableServiceImpl;

public class EditTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditTable() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map map = request.getParameterMap();
		Table tb = new Table();
		try {
			BeanUtils.populate(tb, map);
			TableService service = new TableServiceImpl();
			service.updateTable(tb);
			Gson gson = new Gson();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", 200);

			response.getWriter().write(gson.toJson(result));
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
