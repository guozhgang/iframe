package com.skss.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.skss.app.entity.Menu;
import com.skss.iframe.dao.BaseDao;

@Repository
public class MenuDao extends BaseDao<Menu>{
	public List<Menu> findMenuByAsync(String hql) {
		return findListByHQL(hql);
	}
	public int findCount(String id) {
		return getCountByHql("select count(i) from Menu i where i.parent = "+id);
	}
}
