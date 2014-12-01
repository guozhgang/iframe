package com.skss.test.dao;

import org.springframework.stereotype.Repository;

import com.skss.test.entity.SsUserRole;
import com.skss.iframe.dao.BaseDao;

@Repository
public class SsUserRoleDao extends BaseDao<SsUserRole>{
	public void saveSsUserRole(SsUserRole ssUserRole) {
		this.saveOrUpdate(ssUserRole);
	}
	public void remove(SsUserRole ssUserRole) {
		this.delete(ssUserRole);
	}
}
