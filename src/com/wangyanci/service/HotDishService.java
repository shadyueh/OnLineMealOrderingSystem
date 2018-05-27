package com.wangyanci.service;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.Dish;

public interface HotDishService {

	List<Dish> findHot() throws SQLException;

}
