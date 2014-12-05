package com.skss.app.dao;

import org.springframework.stereotype.Repository;

import com.skss.app.entity.SsUser;
import com.skss.iframe.dao.BaseDao;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SsUserDao extends BaseDao<SsUser>{
	public void saveSsUser(SsUser ssUser) {
		this.saveOrUpdate(ssUser);
	}
	public void remove(SsUser ssUser) {
		this.delete(ssUser);
	}
	public List<SsUser> list(String hql, int start, int rows) {
		return this.findPaginationListByHQL(hql, start, rows);
	} 
	public int count() {
		return this.getCountByHql("select count(i) from SsUser i");
	}
}
