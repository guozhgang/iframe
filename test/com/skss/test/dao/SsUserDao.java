package com.skss.test.dao;

import org.springframework.stereotype.Repository;

import com.skss.test.entity.SsUser;
import com.skss.iframe.dao.BaseDao;

@Repository
public class SsUserDao extends BaseDao<SsUser>{
	public void saveSsUser(SsUser ssUser) {
		this.saveOrUpdate(ssUser);
	}
	public void remove(SsUser ssUser) {
		this.delete(ssUser);
	}
}
