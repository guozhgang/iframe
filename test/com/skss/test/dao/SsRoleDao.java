package com.skss.test.dao;

import org.springframework.stereotype.Repository;

import com.skss.test.entity.SsRole;
import com.skss.iframe.dao.BaseDao;

@Repository
public class SsRoleDao extends BaseDao<SsRole>{
	public void saveSsRole(SsRole ssRole) {
		this.saveOrUpdate(ssRole);
	}
	public void remove(SsRole ssRole) {
		this.delete(ssRole);
	}
}
