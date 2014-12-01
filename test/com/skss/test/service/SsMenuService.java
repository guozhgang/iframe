package com.skss.test.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.test.dao.SsMenuDao;
import com.skss.test.entity.SsMenu;

@Service
public class SsMenuService {
	@Resource
	private SsMenuDao ssMenuDao;

	public void saveSsMenu(SsMenu ssMenu) {
		if (null == ssMenu.getId() || "".equals(ssMenu.getId())) {
			ssMenu.setId(null);
		}
		ssMenuDao.saveSsMenu(ssMenu);
	}
	public void remove(SsMenu ssMenu) {
		ssMenuDao.remove(ssMenu);
	}
}
