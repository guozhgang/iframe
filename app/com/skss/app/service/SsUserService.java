package com.skss.app.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.dao.SsUserDao;
import com.skss.app.entity.SsUser;
import java.util.ArrayList;
import java.util.List;

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
	public List<SsUser> list(SsUser ssUser, int start, int limit) {
		StringBuilder hql = new StringBuilder("from SsUser i where 1 = 1 ");
		return ssUserDao.list(hql.toString(), start, limit);
	}
	public int count() {
		return ssUserDao.count();
	}
}
