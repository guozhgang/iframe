package com.skss.test.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.test.dao.SsRoleMenuDao;
import com.skss.test.entity.SsRoleMenu;

@Service
public class SsRoleMenuService {
	@Resource
	private SsRoleMenuDao ssRoleMenuDao;

	public void saveSsRoleMenu(SsRoleMenu ssRoleMenu) {
		if (null == ssRoleMenu.getId() || "".equals(ssRoleMenu.getId())) {
			ssRoleMenu.setId(null);
		}
		ssRoleMenuDao.saveSsRoleMenu(ssRoleMenu);
	}
	public void remove(SsRoleMenu ssRoleMenu) {
		ssRoleMenuDao.remove(ssRoleMenu);
	}
}
