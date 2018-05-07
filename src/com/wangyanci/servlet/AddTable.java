package com.wangyanci.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.wangyanci.pojo.Table;
import com.wangyanci.service.TableService;
import com.wangyanci.serviceimp.TableServiceImpl;

public class AddTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddTable() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> parammap = new HashMap<String, String[]>();
		for (String key : request.getParameterMap().keySet()) {
			parammap.put(key, request.getParameterMap().get(key));
		}
		parammap.put("id", new String[] { UUID.randomUUID().toString() });// 封装id
		parammap.put("state", new String[] { "1" });
		Table t = new Table();
		TableService service = new TableServiceImpl();
		try {
			BeanUtils.populate(t, parammap);
			service.addTable(t);
			Gson gson = new Gson();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", 200);

			response.getWriter().write(gson.toJson(result));

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
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
