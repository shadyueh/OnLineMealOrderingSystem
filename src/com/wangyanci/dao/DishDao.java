package com.wangyanci.dao;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.Dish;

public interface DishDao {

	public List<Dish> findAll() throws SQLException;

	public Dish findById(String id) throws SQLException;

	public void addDish(Dish p) throws SQLException;

	public List<Dish> findPageList(int page, int rows) throws SQLException;

	public int findAllCount() throws SQLException;

	public void updateDish(Dish p) throws SQLException;

}
