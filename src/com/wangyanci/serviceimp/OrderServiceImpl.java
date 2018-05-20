package com.wangyanci.serviceimp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.wangyanci.dao.DishDao;
import com.wangyanci.dao.DishHotDao;
import com.wangyanci.dao.OrderDao;
import com.wangyanci.dao.OrderDishDao;
import com.wangyanci.dao.OrderTableDao;
import com.wangyanci.dao.TableDao;
import com.wangyanci.dao.UserDao;
import com.wangyanci.daoimp.DisHothDaoImpl;
import com.wangyanci.daoimp.DishDaoImp;
import com.wangyanci.daoimp.OrderDaoimpl;
import com.wangyanci.daoimp.OrderDishDaoImpl;
import com.wangyanci.daoimp.OrderTableDaoImpl;
import com.wangyanci.daoimp.TableDaoImpl;
import com.wangyanci.daoimp.UserDaoImp;
import com.wangyanci.pojo.Cart;
import com.wangyanci.pojo.CartDish;
import com.wangyanci.pojo.Dish;
import com.wangyanci.pojo.Dishhot;
import com.wangyanci.pojo.NoPayOrdersResult;
import com.wangyanci.pojo.OrderDish;
import com.wangyanci.pojo.OrderInfo;
import com.wangyanci.pojo.OrderResult;
import com.wangyanci.pojo.OrderTab;
import com.wangyanci.pojo.Orders;
import com.wangyanci.pojo.Table;
import com.wangyanci.pojo.TablePageListResult;
import com.wangyanci.pojo.User;
import com.wangyanci.service.OrderService;
import com.wangyanci.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	public String buildOrder(User user, Cart cart) {

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
			order.setUser_id(userid);

			OrderDao orderdao = new OrderDaoimpl();
			orderdao.addOrder(order);
			Dishhot dishhot;
			OrderDish orderDish;
			OrderDishDao orderdishdao = new OrderDishDaoImpl();
			DishHotDao dishHotdao = new DisHothDaoImpl();
			for (String dishid : cart.getMap().keySet()) {

				orderDish = new OrderDish();
				orderDish.setOrder_id(orderid);
				orderDish.setDish_id(dishid);
				orderDish.setBuynum(cart.getMap().get(dishid).getCount());
				orderdishdao.addOrderDish(orderDish);

				dishhot = dishHotdao.getDishHotByDishId(dishid);
				if (dishhot == null) {
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
				orderTable.setOrder_id(orderid);
				orderTable.setTab_id(tableid);
				orderTable.setBegintime(cart.getTmap().get(tableid).getBegintime());
				orderTable.setEndtime(cart.getTmap().get(tableid).getEndtime());
				ordertabledao.addOrderTable(orderTable);
			}

			return orderid;

		} catch (SQLException e) {
			e.printStackTrace();
			// 事务回滚
			try {
				DataSourceUtils.rollback(DataSourceUtils.getConnectionByTransaction());
				return "";
			} catch (SQLException e1) {
				e1.printStackTrace();
				return "";
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

	public List<OrderInfo> gerNoPayOrder(int uid) throws SQLException {

		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		OrderInfo orderInfo;
		UserDao userDao = new UserDaoImp();
		DishDao dishDao = new DishDaoImp();
		TableDao tableDao = new TableDaoImpl();
		OrderDao orderDao = new OrderDaoimpl();
		OrderDishDao orderDishDao = new OrderDishDaoImpl();
		OrderTableDao orderTableDao = new OrderTableDaoImpl();

		List<Orders> orders = orderDao.findNoPayOrders(uid);
		System.out.println("~~~~~~~~~orders~~~~~~~~" + orders);
		User user = userDao.findUserById(uid);

		for (Orders order : orders) {
			orderInfo = new OrderInfo();
			orderInfo.setUser(user);
			orderInfo.setId(order.getId());
			orderInfo.setTotal(order.getMoney());
			orderInfo.setOrdertime(order.getOrdertime());
			orderInfo.setPaystate(order.getPaystate());

			Map<String, CartDish> dishinfo_map = new HashMap<String, CartDish>();
			System.out.println("~~~~~~~~~order.getId()~~~~~~~~" + order.getId());
			List<OrderDish> orderDishs = orderDishDao.findDish(order.getId());
			System.out.println("~~~~~~~~~orderDishs~~~~~~~~" + orderDishs);
			for (OrderDish orderDish : orderDishs) {
				CartDish cartDish = new CartDish();

				Dish dish = dishDao.findById(orderDish.getDish_id());
				System.out.println("~~~~~~~~~dish~~~~~~~~" + dish);
				cartDish.setCount(orderDish.getBuynum());
				cartDish.setDish(dish);
				dishinfo_map.put(orderDish.getDish_id(), cartDish);
			}
			orderInfo.setDishinfo_map(dishinfo_map);

			Map<String, Table> tbMap = new HashMap<String, Table>();
			System.out.println("~~~~~~~~~order.getId()~~~~~~~~" + order.getId());
			List<OrderTab> orderTabs = orderTableDao.findTable(order.getId());
			System.out.println("~~~~~~~~~orderTabs~~~~~~~~" + orderTabs);
			for (OrderTab orderTab : orderTabs) {
				Table table = tableDao.findById(orderTab.getTab_id());
				System.out.println("~~~~~~~~~table~~~~~~~~" + table);
				tbMap.put(orderTab.getTab_id(), table);
			}
			orderInfo.setTab_map(tbMap);
			orderInfos.add(orderInfo);

		}
		return orderInfos;

	}

	public NoPayOrdersResult gerNoPayOrder(int uid, int page, int rows) throws SQLException {
		NoPayOrdersResult nopayresult = new NoPayOrdersResult();
		OrderDao orderDao = new OrderDaoimpl();
		int count = orderDao.findNoPayOrdersCount(uid);
		int totalPage = (int) Math.ceil(count * 1.0 / rows);

		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		OrderInfo orderInfo;
		UserDao userDao = new UserDaoImp();
		DishDao dishDao = new DishDaoImp();
		TableDao tableDao = new TableDaoImpl();
		OrderDishDao orderDishDao = new OrderDishDaoImpl();
		OrderTableDao orderTableDao = new OrderTableDaoImpl();

		List<Orders> orders = orderDao.findNoPayOrdersByPage(uid, page, rows);
		System.out.println("~~~~~~~~~orders~~~~~~~~" + orders);
		User user = userDao.findUserById(uid);

		for (Orders order : orders) {
			orderInfo = new OrderInfo();
			orderInfo.setUser(user);
			orderInfo.setId(order.getId());
			orderInfo.setTotal(order.getMoney());
			orderInfo.setOrdertime(order.getOrdertime());
			orderInfo.setPaystate(order.getPaystate());

			Map<String, CartDish> dishinfo_map = new HashMap<String, CartDish>();
			System.out.println("~~~~~~~~~order.getId()~~~~~~~~" + order.getId());
			List<OrderDish> orderDishs = orderDishDao.findDish(order.getId());
			System.out.println("~~~~~~~~~orderDishs~~~~~~~~" + orderDishs);
			for (OrderDish orderDish : orderDishs) {
				CartDish cartDish = new CartDish();

				Dish dish = dishDao.findById(orderDish.getDish_id());
				System.out.println("~~~~~~~~~dish~~~~~~~~" + dish);
				cartDish.setCount(orderDish.getBuynum());
				cartDish.setDish(dish);
				dishinfo_map.put(orderDish.getDish_id(), cartDish);
			}
			orderInfo.setDishinfo_map(dishinfo_map);

			Map<String, Table> tbMap = new HashMap<String, Table>();
			System.out.println("~~~~~~~~~order.getId()~~~~~~~~" + order.getId());
			List<OrderTab> orderTabs = orderTableDao.findTable(order.getId());
			System.out.println("~~~~~~~~~orderTabs~~~~~~~~" + orderTabs);
			for (OrderTab orderTab : orderTabs) {
				Table table = tableDao.findById(orderTab.getTab_id());
				System.out.println("~~~~~~~~~table~~~~~~~~" + table);
				tbMap.put(orderTab.getTab_id(), table);
			}
			orderInfo.setTab_map(tbMap);
			orderInfos.add(orderInfo);

		}

		nopayresult.setTotalPage(totalPage);
		nopayresult.setTotalCount(count);
		nopayresult.setPage(page);
		nopayresult.setRows(rows);
		nopayresult.setOrderInfos(orderInfos);

		return nopayresult;

	}

	public OrderInfo findOrderByiD(String orderid) throws SQLException {

		OrderInfo orderInfo;
		UserDao userDao = new UserDaoImp();
		DishDao dishDao = new DishDaoImp();
		TableDao tableDao = new TableDaoImpl();
		OrderDao orderDao = new OrderDaoimpl();
		OrderDishDao orderDishDao = new OrderDishDaoImpl();
		OrderTableDao orderTableDao = new OrderTableDaoImpl();

		Orders order = orderDao.findOrderById(orderid);

		User user = userDao.findUserById(order.getUser_id());

		orderInfo = new OrderInfo();
		orderInfo.setUser(user);
		orderInfo.setId(order.getId());
		orderInfo.setTotal(order.getMoney());
		orderInfo.setOrdertime(order.getOrdertime());
		orderInfo.setPaystate(order.getPaystate());

		Map<String, CartDish> dishinfo_map = new HashMap<String, CartDish>();
		System.out.println("~~~~~~~~~order.getId()~~~~~~~~" + order.getId());
		List<OrderDish> orderDishs = orderDishDao.findDish(order.getId());
		System.out.println("~~~~~~~~~orderDishs~~~~~~~~" + orderDishs);
		for (OrderDish orderDish : orderDishs) {
			CartDish cartDish = new CartDish();

			Dish dish = dishDao.findById(orderDish.getDish_id());
			System.out.println("~~~~~~~~~dish~~~~~~~~" + dish);
			cartDish.setCount(orderDish.getBuynum());
			cartDish.setDish(dish);
			dishinfo_map.put(orderDish.getDish_id(), cartDish);
		}
		orderInfo.setDishinfo_map(dishinfo_map);

		Map<String, Table> tbMap = new HashMap<String, Table>();
		System.out.println("~~~~~~~~~order.getId()~~~~~~~~" + order.getId());
		List<OrderTab> orderTabs = orderTableDao.findTable(order.getId());
		System.out.println("~~~~~~~~~orderTabs~~~~~~~~" + orderTabs);
		for (OrderTab orderTab : orderTabs) {
			Table table = tableDao.findById(orderTab.getTab_id());
			System.out.println("~~~~~~~~~table~~~~~~~~" + table);
			tbMap.put(orderTab.getTab_id(), table);
		}
		orderInfo.setTab_map(tbMap);

		return orderInfo;
	}

	public void pay(String orderid) throws SQLException {
		OrderDao dao = new OrderDaoimpl();
		dao.pay(orderid);

	}

	public NoPayOrdersResult gerPayOrder(int uid, int page, int rows) throws SQLException {
		NoPayOrdersResult nopayresult = new NoPayOrdersResult();
		OrderDao orderDao = new OrderDaoimpl();
		int count = orderDao.findPayOrdersCount(uid);
		int totalPage = (int) Math.ceil(count * 1.0 / rows);

		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		OrderInfo orderInfo;
		UserDao userDao = new UserDaoImp();
		DishDao dishDao = new DishDaoImp();
		TableDao tableDao = new TableDaoImpl();
		OrderDishDao orderDishDao = new OrderDishDaoImpl();
		OrderTableDao orderTableDao = new OrderTableDaoImpl();

		List<Orders> orders = orderDao.findPayOrdersByPage(uid, page, rows);
		System.out.println("~~~~~~~~~orders~~~~~~~~" + orders);
		User user = userDao.findUserById(uid);

		for (Orders order : orders) {
			orderInfo = new OrderInfo();
			orderInfo.setUser(user);
			orderInfo.setId(order.getId());
			orderInfo.setTotal(order.getMoney());
			orderInfo.setOrdertime(order.getOrdertime());
			orderInfo.setPaystate(order.getPaystate());

			Map<String, CartDish> dishinfo_map = new HashMap<String, CartDish>();
			System.out.println("~~~~~~~~~order.getId()~~~~~~~~" + order.getId());
			List<OrderDish> orderDishs = orderDishDao.findDish(order.getId());
			System.out.println("~~~~~~~~~orderDishs~~~~~~~~" + orderDishs);
			for (OrderDish orderDish : orderDishs) {
				CartDish cartDish = new CartDish();

				Dish dish = dishDao.findById(orderDish.getDish_id());
				System.out.println("~~~~~~~~~dish~~~~~~~~" + dish);
				cartDish.setCount(orderDish.getBuynum());
				cartDish.setDish(dish);
				dishinfo_map.put(orderDish.getDish_id(), cartDish);
			}
			orderInfo.setDishinfo_map(dishinfo_map);

			Map<String, Table> tbMap = new HashMap<String, Table>();
			System.out.println("~~~~~~~~~order.getId()~~~~~~~~" + order.getId());
			List<OrderTab> orderTabs = orderTableDao.findTable(order.getId());
			System.out.println("~~~~~~~~~orderTabs~~~~~~~~" + orderTabs);
			for (OrderTab orderTab : orderTabs) {
				Table table = tableDao.findById(orderTab.getTab_id());
				System.out.println("~~~~~~~~~table~~~~~~~~" + table);
				tbMap.put(orderTab.getTab_id(), table);
			}
			orderInfo.setTab_map(tbMap);
			orderInfos.add(orderInfo);

		}

		nopayresult.setTotalPage(totalPage);
		nopayresult.setTotalCount(count);
		nopayresult.setPage(page);
		nopayresult.setRows(rows);
		nopayresult.setOrderInfos(orderInfos);

		return nopayresult;

	}

	public TablePageListResult gerAllOrder(int page, int rows) throws SQLException {
		TablePageListResult result = new TablePageListResult();
		User user;
		List<OrderResult> rowslist = new ArrayList<OrderResult>();
		OrderResult orderResult;
		OrderDao orderDao = new OrderDaoimpl();
		UserDao userDao = new UserDaoImp();

		int count = orderDao.getAllOrderCount();
		List<Orders> orders = orderDao.getAllOrdersByPage(page, rows);

		for (Orders order : orders) {
			orderResult = new OrderResult();
			user = userDao.findUserById(order.getUser_id());
			orderResult.setMoney(order.getMoney());
			orderResult.setNickname(user.getNickname());
			orderResult.setOrderid(order.getId());
			orderResult.setOrdertime(order.getOrdertime());
			orderResult.setPaystate(order.getPaystate());
			orderResult.setReceiverinfo(order.getReceiverinfo());
			orderResult.setUser_id(order.getUser_id());
			orderResult.setUsername(user.getUsername());
			rowslist.add(orderResult);
		}

		result.setTotal(count);
		result.setRows(rowslist);

		return result;

	}

}