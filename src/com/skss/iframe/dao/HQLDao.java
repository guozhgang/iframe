package com.skss.iframe.dao;

import java.util.List;

public interface HQLDao <T> {
	public abstract List<T> findListByHQL(String hql, Object... objs);
	
	public abstract List<T> findPaginationListByHQL(String hql, int start, int limit, Object... objs);   //分页查询
	
	public abstract List<T> executeByHQL(String hql);
	
	public abstract int getCountByHql(String hql,Object... objs);
	
	
}
