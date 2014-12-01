package com.skss.app.dao;

import org.springframework.stereotype.Repository;

import com.skss.app.entity.Test;
import com.skss.iframe.dao.BaseDao;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TestDao extends BaseDao<Test>{
	public void saveTest(Test test) {
		this.saveOrUpdate(test);
	}
	public void remove(Test test) {
		this.delete(test);
	}
	public List<Test> list(String hql, int start, int rows) {
		return this.findPaginationListByHQL(hql, start, rows);
	} 
	public int count() {
		return this.getCountByHql("select count(i) from Test i");
	}
}
