package com.skss.app.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.dao.CityDao;
import com.skss.app.entity.City;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
	@Resource
	private CityDao cityDao;

	public void saveCity(City city) {
		if (null == city.getId() || "".equals(city.getId())) {
			city.setId(null);
		}
		cityDao.saveCity(city);
	}
	public void remove(City city) {
		cityDao.remove(city);
	}
	public List<City> list(City city, int start, int limit) {
		StringBuilder hql = new StringBuilder("from City i where 1 = 1 ");
		return cityDao.list(hql.toString(), start, limit);
	}
	public int count() {
		return cityDao.count();
	}
}
