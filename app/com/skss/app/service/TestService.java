package com.skss.app.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.dao.TestDao;
import com.skss.app.entity.Test;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
	@Resource
	private TestDao testDao;

	public void saveTest(Test test) {
		if (null == test.getId() || "".equals(test.getId())) {
			test.setId(null);
		}
		testDao.saveTest(test);
	}
	public void remove(Test test) {
		testDao.remove(test);
	}
	public List<Test> list(Test test, int start, int limit) {
		StringBuilder hql = new StringBuilder("from Test i where 1 = 1 ");
		return testDao.list(hql.toString(), start, limit);
	}
	public int count() {
		return testDao.count();
	}
}
