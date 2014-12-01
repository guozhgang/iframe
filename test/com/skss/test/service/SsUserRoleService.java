package com.skss.test.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.test.dao.SsUserRoleDao;
import com.skss.test.entity.SsUserRole;

@Service
public class SsUserRoleService {
	@Resource
	private SsUserRoleDao ssUserRoleDao;

	public void saveSsUserRole(SsUserRole ssUserRole) {
		if (null == ssUserRole.getId() || "".equals(ssUserRole.getId())) {
			ssUserRole.setId(null);
		}
		ssUserRoleDao.saveSsUserRole(ssUserRole);
	}
	public void remove(SsUserRole ssUserRole) {
		ssUserRoleDao.remove(ssUserRole);
	}
}
