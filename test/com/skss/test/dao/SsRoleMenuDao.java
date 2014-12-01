package com.skss.test.dao;

import org.springframework.stereotype.Repository;

import com.skss.test.entity.SsRoleMenu;
import com.skss.iframe.dao.BaseDao;

@Repository
public class SsRoleMenuDao extends BaseDao<SsRoleMenu>{
	public void saveSsRoleMenu(SsRoleMenu ssRoleMenu) {
		this.saveOrUpdate(ssRoleMenu);
	}
	public void remove(SsRoleMenu ssRoleMenu) {
		this.delete(ssRoleMenu);
	}
}
