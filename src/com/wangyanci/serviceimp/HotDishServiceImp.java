package com.wangyanci.serviceimp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wangyanci.dao.DishDao;
import com.wangyanci.dao.DishHotDao;
import com.wangyanci.daoimp.DisHothDaoImpl;
import com.wangyanci.daoimp.DishDaoImp;
import com.wangyanci.pojo.Dish;
import com.wangyanci.pojo.Dishhot;
import com.wangyanci.service.HotDishService;

public class HotDishServiceImp implements HotDishService {

	public List<Dish> findHot() throws SQLException {
		List<Dish> dishs = new ArrayList<Dish>();
		DishHotDao dao = new DisHothDaoImpl();
		DishDao dishDao = new DishDaoImp();
		List<Dishhot> dishhots = dao.findHot();

		for (Dishhot dishhot : dishhots) {
			Dish dish = dishDao.findById(dishhot.getDishid());
			dishs.add(dish);
		}

		if (dishs.size() <= 10) {
			if (dishs != null) {
				System.out.println("@@@@@@@@hot dish mount =======" + dishs.size());
				System.out.println("@@@@@@@@hot dish nedd mount =======" + (10 - dishs.size()));
				dishs.addAll(dishDao.findPageList(1, 10 - dishs.size()));
			}

		}
		return dishs;
	}
}
