package com.wangyanci.serviceimp;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.dao.DishDao;
import com.wangyanci.daoimp.DishDaoImp;
import com.wangyanci.pojo.Dish;
import com.wangyanci.pojo.PageDish;
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

	public PageDish findPageList(int page, int rows) throws SQLException {
		PageDish pd = new PageDish();

		DishDao dishdao = new DishDaoImp();
		List<Dish> dishs = dishdao.findPageList(page, rows);
		pd.setDishs(dishs);

		int totalCount = dishdao.findAllCount();
		int totalPage = (int) Math.ceil(totalCount * 1.0 / rows);

		pd.setTotalCount(totalCount);
		pd.setTotalPage(totalPage);
		pd.setRows(rows);
		pd.setPage(page);
		return pd;

	}

}
