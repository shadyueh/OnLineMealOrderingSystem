package com.wangyanci.serviceimp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.wangyanci.dao.DishHotDao;
import com.wangyanci.dao.OrderDao;
import com.wangyanci.dao.OrderDishDao;
import com.wangyanci.dao.OrderTableDao;
import com.wangyanci.daoimp.DisHothDaoImpl;
import com.wangyanci.daoimp.OrderDaoimpl;
import com.wangyanci.daoimp.OrderDishDaoImpl;
import com.wangyanci.daoimp.OrderTableDaoImpl;
import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.Dishhot;
import com.wangyanci.pojo.OrderDish;
import com.wangyanci.pojo.OrderTab;
import com.wangyanci.pojo.Orders;
import com.wangyanci.pojo.User;
import com.wangyanci.service.OrderService;
import com.wangyanci.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	public void buildOrder(User user, Cart cart) {

		try {
			DataSourceUtils.startTransaction(DataSourceUtils.getConnectionByTransaction());

			String orderid = UUID.randomUUID().toString();
			int userid = user.getId();
			double money = cart.getTotal();
			int paystate = 0;
			Date paydata = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				paydata = sdf.parse(sdf.format(new Date()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Orders order = new Orders();
			order.setId(orderid);
			order.setMoney(money);
			order.setOrdertime(paydata);
			order.setPaystate(paystate);
			order.setUserId(userid);

			OrderDao orderdao = new OrderDaoimpl();
			orderdao.addOrder(order);
			Dishhot dishhot;
			OrderDish orderDish;
			OrderDishDao orderdishdao = new OrderDishDaoImpl();
			DishHotDao dishHotdao = new DisHothDaoImpl();
			for (String dishid : cart.getMap().keySet()) {

				orderDish = new OrderDish();
				orderDish.setOrderId(orderid);
				orderDish.setDishId(dishid);
				orderDish.setBuynum(cart.getMap().get(dishid).getCount());
				orderdishdao.addOrderDish(orderDish);

				dishhot = dishHotdao.getDishHotByDishId(dishid);
				if (dishhot.getDishid() == null) {
					dishhot = new Dishhot();
					dishhot.setDishid(dishid);
					dishhot.setNum(cart.getMap().get(dishid).getCount());
					dishHotdao.addDishHot(dishhot);
				} else {

					dishhot.setNum(dishhot.getNum() + cart.getMap().get(dishid).getCount());
					dishHotdao.updateDishHotNum(dishhot);

				}

			}

			OrderTab orderTable;
			OrderTableDao ordertabledao = new OrderTableDaoImpl();
			for (String tableid : cart.getTmap().keySet()) {
				orderTable = new OrderTab();
				orderTable.setOrderId(orderid);
				orderTable.setTabId(tableid);
				orderTable.setBegintime(cart.getTmap().get(tableid).getBegintime());
				orderTable.setEndtime(cart.getTmap().get(tableid).getEndtime());
				ordertabledao.addOrderTable(orderTable);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// 事务回滚
			try {
				DataSourceUtils.rollback(DataSourceUtils.getConnectionByTransaction());
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				// 释放资源,并且事务提交以及从ThreadLocal中移除Connection。
				try {
					DataSourceUtils.closeConnection(DataSourceUtils.getConnectionByTransaction());
				} catch (SQLException e1) {
					e.printStackTrace();
				}
			}

		}

	}
}