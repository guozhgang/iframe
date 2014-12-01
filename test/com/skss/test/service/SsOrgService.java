package com.skss.test.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.test.dao.SsOrgDao;
import com.skss.test.entity.SsOrg;

@Service
public class SsOrgService {
	@Resource
	private SsOrgDao ssOrgDao;

	public void saveSsOrg(SsOrg ssOrg) {
		if (null == ssOrg.getId() || "".equals(ssOrg.getId())) {
			ssOrg.setId(null);
		}
		ssOrgDao.saveSsOrg(ssOrg);
	}
	public void remove(SsOrg ssOrg) {
		ssOrgDao.remove(ssOrg);
	}
}
