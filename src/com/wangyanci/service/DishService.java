package com.wangyanci.service;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.Dish;
import com.wangyanci.pojo.PageDish;

public interface DishService {

	public List<Dish> findAll() throws SQLException;

	public Dish findById(String id) throws SQLException;

	public void addProduct(Dish p) throws SQLException;

	public PageDish findPageList(int page, int rows) throws SQLException;

	public void updateDish(Dish p) throws SQLException;

	public void deleteById(String id) throws SQLException;

	public void updateDishState(String id, int state) throws SQLException;

}
