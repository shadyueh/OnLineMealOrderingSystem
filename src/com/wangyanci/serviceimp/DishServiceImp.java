package com.wangyanci.serviceimp;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.dao.DishDao;
import com.wangyanci.daoimp.DishDaoImp;
import com.wangyanci.pojo.Dish;
import com.wangyanci.service.DishService;

public class DishServiceImp implements DishService {

	public List<Dish> findAll() throws SQLException {
		DishDao dishdao = new DishDaoImp();
		List<Dish> dishes = dishdao.findAll();
		return dishes;
	}

	public Dish findById(String id) throws SQLException {
		DishDao dishdao = new DishDaoImp();
		Dish dish = dishdao.findById(id);
		return dish;
	}

	public void addProduct(Dish p) throws SQLException {
		DishDao dishdao = new DishDaoImp();
		dishdao.addDish(p);

	}

}
