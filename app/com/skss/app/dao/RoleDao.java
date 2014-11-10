package com.skss.app.dao;

import org.springframework.stereotype.Repository;

import com.skss.app.entity.Role;
import com.skss.iframe.dao.BaseDao;

@Repository
public class RoleDao extends BaseDao<Role>{
	public void saveRole(Role role) {
		this.saveOrUpdate(role);
	}
}
