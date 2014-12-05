package com.skss.app.dao;

import org.springframework.stereotype.Repository;

import com.skss.app.entity.City;
import com.skss.iframe.dao.BaseDao;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityDao extends BaseDao<City>{
	public void saveCity(City city) {
		this.saveOrUpdate(city);
	}
	public void remove(City city) {
		this.delete(city);
	}
	public List<City> list(String hql, int start, int rows) {
		return this.findPaginationListByHQL(hql, start, rows);
	} 
	public int count() {
		return this.getCountByHql("select count(i) from City i");
	}
}
