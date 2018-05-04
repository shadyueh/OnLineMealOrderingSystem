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

	public void deleteById(String id) throws SQLException;

	public void updateDishState(String id, int state) throws SQLException;

	public int findAllCountByText(String paramkey, String paramvalue) throws SQLException;

	public List<Dish> findPageListByText(int page, int rows, String paramkey, String paramvalue) throws SQLException;

	public int findAllCountByLike(String paramkey, String paramvalue) throws SQLException;

	public List<Dish> findPageListByLike(int page, int rows, String paramkey, String paramvalue) throws SQLException;

	public int findAllCountByNum(String paramkey, int paramvalue1) throws SQLException;

	public List<Dish> findPageListByNum(int page, int rows, String paramkey, int paramvalue1) throws SQLException;

	public int findAllCountByBetweenPrice(String paramkey, int parseInt, int parseInt2) throws SQLException;

	public List<Dish> findPageListByBetweenPrice(int page, int rows, String paramkey, int parseInt, int parseInt2)
			throws SQLException;

	public int findAllCountByBetweenPnum(String paramkey, int parseInt, int parseInt2) throws SQLException;

	public List<Dish> findPageListByBetweenPnum(int page, int rows, String paramkey, int parseInt, int parseInt2)
			throws SQLException;

}
