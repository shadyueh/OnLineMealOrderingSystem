package com.wangyanci.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.Table;
import com.wangyanci.service.TableService;
import com.wangyanci.serviceimp.TableServiceImpl;

public class SureTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SureTable() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = request.getParameter("ids");
		String[] strs = str.split(",");

		System.out.println("*****stime*****" + request.getParameter("stime") + "#########etime#####"
				+ request.getParameter("etime"));

		Date sData = null;

		try {
			sData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("stime"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Date eData = null;
		try {
			eData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("etime"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		System.out.println("*****stime*****" + sData + "#########etime#####" + eData);
		TableService service = new TableServiceImpl();
		try {
			service.sureTable(strs, sData, eData);
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			for (String id : strs) {
				if (cart.getTmap() == null) {
					Map<String, Table> map = new HashMap<String, Table>();
					cart.setTmap(map);
				}
				cart.getTmap().put(id, service.findById(id));

			}

			response.getWriter().write("ok");
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
