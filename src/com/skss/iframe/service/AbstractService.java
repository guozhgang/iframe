package com.skss.iframe.service;


import javax.annotation.Resource;


import com.skss.iframe.dao.BaseDao;

public class AbstractService <T>{
	@Resource
	private BaseDao<T> baseDao;
	public void save(T t) {
		this.baseDao.save(t);
	}
}
