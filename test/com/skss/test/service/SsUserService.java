package com.skss.test.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.test.dao.SsUserDao;
import com.skss.test.entity.SsUser;

@Service
public class SsUserService {
	@Resource
	private SsUserDao ssUserDao;

	public void saveSsUser(SsUser ssUser) {
		if (null == ssUser.getId() || "".equals(ssUser.getId())) {
			ssUser.setId(null);
		}
		ssUserDao.saveSsUser(ssUser);
	}
	public void remove(SsUser ssUser) {
		ssUserDao.remove(ssUser);
	}
}
