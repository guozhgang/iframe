package com.skss.test.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.test.dao.SsRoleDao;
import com.skss.test.entity.SsRole;

@Service
public class SsRoleService {
	@Resource
	private SsRoleDao ssRoleDao;

	public void saveSsRole(SsRole ssRole) {
		if (null == ssRole.getId() || "".equals(ssRole.getId())) {
			ssRole.setId(null);
		}
		ssRoleDao.saveSsRole(ssRole);
	}
	public void remove(SsRole ssRole) {
		ssRoleDao.remove(ssRole);
	}
}
