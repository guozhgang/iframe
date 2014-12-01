package com.skss.test.dao;

import org.springframework.stereotype.Repository;

import com.skss.test.entity.SsOrg;
import com.skss.iframe.dao.BaseDao;

@Repository
public class SsOrgDao extends BaseDao<SsOrg>{
	public void saveSsOrg(SsOrg ssOrg) {
		this.saveOrUpdate(ssOrg);
	}
	public void remove(SsOrg ssOrg) {
		this.delete(ssOrg);
	}
}
