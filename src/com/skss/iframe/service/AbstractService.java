package com.skss.iframe.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.entity.Organization;
import com.skss.iframe.dao.BaseDao;

public class AbstractService {
	@Resource
	private BaseDao<Organization> baseDao;
	public void save(Organization t) {
		this.baseDao.save(t);
	}
}
