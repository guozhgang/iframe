package com.skss.app.dao;

import org.springframework.stereotype.Repository;

import com.skss.app.entity.SsOrg;
import com.skss.iframe.dao.BaseDao;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SsOrgDao extends BaseDao<SsOrg>{
	public void saveSsOrg(SsOrg ssOrg) {
		this.saveOrUpdate(ssOrg);
	}
	public void remove(SsOrg ssOrg) {
		this.delete(ssOrg);
	}
	public List<SsOrg> list(String hql, int start, int rows) {
		return this.findPaginationListByHQL(hql, start, rows);
	} 
	public int count() {
		return this.getCountByHql("select count(i) from SsOrg i");
	}
}
