package com.wangyanci.service;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.Dish;

public interface DishService {

	public List<Dish> findAll() throws SQLException;

	public Dish findById(String id) throws SQLException;

}
