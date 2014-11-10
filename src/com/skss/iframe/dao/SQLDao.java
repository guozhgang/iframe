package com.skss.iframe.dao;

import java.util.List;

public interface SQLDao<T> {

	public abstract List<T> findListBySQL(String sql, Object... objs);
	
	public abstract List<T> findPaginationListBySQL(String sql, int start, int limit, Object... objs);   //分页查询
	
	public abstract List<T> executeBySQL(String sql);
	
	public abstract int getCountBySql(String sql,Object... objs);
	
	public abstract T load(String id);
	
	public abstract T get(String id);
	
	public abstract void save(T t) ;
	
	public abstract void update(T t);
	
	public abstract void saveOrUpdate(T t);
	
	public abstract void delete(T t);

}