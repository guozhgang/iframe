package com.skss.app.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.dao.SsOrgDao;
import com.skss.app.entity.SsOrg;
import java.util.ArrayList;
import java.util.List;

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
	public List<SsOrg> list(SsOrg ssOrg, int start, int limit) {
		StringBuilder hql = new StringBuilder("from SsOrg i where 1 = 1 ");
		return ssOrgDao.list(hql.toString(), start, limit);
	}
	public int count() {
		return ssOrgDao.count();
	}
}
