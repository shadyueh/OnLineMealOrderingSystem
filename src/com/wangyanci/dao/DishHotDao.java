package com.wangyanci.dao;

import java.sql.SQLException;
import java.util.List;

import com.wangyanci.pojo.Dishhot;

public interface DishHotDao {

	void addDishHot(Dishhot dishhot) throws SQLException;

	Dishhot getDishHotByDishId(String dishid) throws SQLException;

	void updateDishHotNum(Dishhot dishhot) throws SQLException;

	List<Dishhot> findHot() throws SQLException;

}
