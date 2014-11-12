package com.skss.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.dao.OrgDao;
import com.skss.app.entity.Organization;


@Service
public class OrgService {
	@Resource
	private OrgDao orgDao;
	
	public void save(Organization org) {
		if (org.getId() == null) {
			org.setId(null);
		}
		this.orgDao.saveOrUpdate(org);
	}
	
	public List<Organization> list (String id) {
		StringBuilder hql = new StringBuilder("from Organization where 1 = 1 ");
		if (!"".equals(id) && null != id) {
			hql.append("and parent = '" + id + "'");
		} else {
			hql.append("and parent = ''");
		}
		return this.orgDao.findListByHQL(hql.toString());
	}
	public int count(String parent) {
		StringBuilder hql = new StringBuilder("select count(i) from Organization i where 1 = 1 ");
		if (!"".equals(parent)) {
			hql.append("and parent = '"+parent+"'");
		}
		return this.orgDao.getCountByHql(hql.toString());
	}
}
