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
		if (dishs.size() < rows) {
			for (int i = dishs.size(); i < rows; i++) {
				Dish dish = new Dish();
				dishs.add(dish);
			}

		}
		pd.setDishs(dishs);

		int totalCount = dishdao.findAllCount();
		int totalPage = (int) Math.ceil(totalCount * 1.0 / rows);
		pd.setParamkey("null");
		pd.setParamvalue("null");
		pd.setTotalCount(totalCount);
		pd.setTotalPage(totalPage);
		pd.setRows(rows);
		pd.setPage(page);
		return pd;

	}

	public void updateDish(Dish p) throws SQLException {
		DishDao dishdao = new DishDaoImp();
		dishdao.updateDish(p);
	}

	public void deleteById(String id) throws SQLException {
		DishDao dishdao = new DishDaoImp();
		dishdao.deleteById(id);

	}

	public void updateDishState(String id, int state) throws SQLException {
		DishDao dishdao = new DishDaoImp();
		dishdao.updateDishState(id, state);

	}

	public PageDish findPageListByCondtion(int page, int rows, String paramkey, String paramvalue) throws SQLException {

		PageDish pd = new PageDish();

		DishDao dishdao = new DishDaoImp();

		int totalCount;
		List<Dish> dishs;

		if (paramkey.equals("state")) {
			int paramvalue1;
			System.out.println("--------paramvalue1-----------" + paramvalue);
			if (paramvalue.equals("已上架")) {
				paramvalue1 = 1;
			} else {
				paramvalue1 = 0;

			}
			totalCount = dishdao.findAllCountByNum(paramkey, paramvalue1);
			dishs = dishdao.findPageListByNum(page, rows, paramkey, paramvalue1);
			System.out.println("状态信息从数据库条件查询到----------------------" + dishs);
		} else if (paramkey.equals("name")) {
			System.out.println("--------name-----------" + paramvalue);
			totalCount = dishdao.findAllCountByLike(paramkey, paramvalue);
			dishs = dishdao.findPageListByLike(page, rows, paramkey, paramvalue);
			System.out.println("状态信息从数据库条件查询到----------------------" + dishs);
		} else if (paramkey.equals("category")) {

			totalCount = dishdao.findAllCountByText(paramkey, paramvalue);
			dishs = dishdao.findPageListByText(page, rows, paramkey, paramvalue);

		} else if (paramkey.equals("price")) {
			String[] strs = paramvalue.split("~");
			totalCount = dishdao.findAllCountByBetweenPrice(paramkey, Integer.parseInt(strs[0]),
					Integer.parseInt(strs[1]));
			dishs = dishdao.findPageListByBetweenPrice(page, rows, paramkey, Integer.parseInt(strs[0]),
					Integer.parseInt(strs[1]));
		} else {
			String[] strs = paramvalue.split("~");
			totalCount = dishdao.findAllCountByBetweenPnum(paramkey, Integer.parseInt(strs[0]),
					Integer.parseInt(strs[1]));
			dishs = dishdao.findPageListByBetweenPnum(page, rows, paramkey, Integer.parseInt(strs[0]),
					Integer.parseInt(strs[1]));
		}

		if (dishs.size() < rows) {
			for (int i = dishs.size(); i < rows; i++) {
				Dish dish = new Dish();
				dishs.add(dish);
			}

		}
		System.out.println("从数据库条件查询到----------------------" + dishs);
		pd.setDishs(dishs);
		pd.setParamkey(paramkey);
		pd.setParamvalue(paramvalue);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / rows);

		pd.setTotalCount(totalCount);
		pd.setTotalPage(totalPage);
		pd.setRows(rows);
		pd.setPage(page);
		return pd;

	}

}
