package com.skss.iframe.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.skss.iframe.constant.cache.QueryCache;
import com.skss.iframe.util.SessionUtil;

/**
 * BaseDao
 * @author guozhgang
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDao <T> extends SessionUtil implements SQLDao<T>,HQLDao<T> {
 
	public List<T> findListBySQL(String sql,Object... objs){
		SQLQuery query = getCurrentSession().createSQLQuery(formatSQL(sql));
		fillParam(query,objs);
		query.setCacheable(QueryCache.QUERY_CACHE_TRUE);
		return query.addEntity(getEntity()).list();
	}
	public List<T> executeBySQL(String sql){
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setCacheable(QueryCache.QUERY_CACHE_TRUE);
		return query.addEntity(getEntity()).list();
	}
	public List<T> findPaginationListBySQL(String sql, int start, int limit, Object... objs) {
		SQLQuery query = getCurrentSession().createSQLQuery(formatSQL(sql));
		query.setFirstResult(start);
		query.setMaxResults(limit);
		fillParam(query, objs);
		query.setCacheable(QueryCache.QUERY_CACHE_TRUE);
		return query.addEntity(getEntity()).list();
	}
	private Class<?> getEntity(){
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private static String formatSQL(String sql){
		int num = getSubstrByIndex(sql,"?",0);
		for(int i=0;i<num;i++){
			sql=sql.replaceFirst("[?]", ":param"+i);
		}
		
		return sql;
	}
	private static int getSubstrByIndex(String sql,String str,int fromIndex){
		int index=sql.indexOf(str, fromIndex);
		int num = 0;
		if(index!=-1){
			num = getSubstrByIndex(sql,str,index+1)+1;
		}
		return num;
	}
	private void fillParam(Query query, Object... objs){
		if(objs instanceof Object){
			for(int i=0;i<objs.length;i++){
				query.setParameter("param"+i, objs[i]);
			}
		}
	}
	public int getCountBySql(String sql, Object... objs) {
		SQLQuery query=getCurrentSession().createSQLQuery(formatSQL(sql));
		fillParam(query, objs);
		query.setCacheable(QueryCache.QUERY_CACHE_TRUE);
		//query.addEntity(getEntity());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	public T load(String id) {
		T t = (T)getCurrentSession().load(getEntity(), id);
		return t;
	}
	public T get(String id) {
		T t = (T)getCurrentSession().get(getEntity(), id);
		return t;
	}
	public void save(T t) {
		getCurrentSession().save(t);
	}
	public void update(T t) {
		getCurrentSession().update(t);
	}
	public void saveOrUpdate(T t) {
		 getCurrentSession().saveOrUpdate(t);
	}
	public void delete(T t) {
		getCurrentSession().delete(t);
	}
	@Override
	public List<T> findListByHQL(String hql, Object... objs) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(formatSQL(hql));
		fillParam(query, objs);
		query.setCacheable(QueryCache.QUERY_CACHE_TRUE);
		return query.list();
	}
	@Override
	public List<T> findPaginationListByHQL(String hql, int start, int limit,
			Object... objs) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(formatSQL(hql));
		query.setFirstResult(start);
		query.setMaxResults(limit);
		fillParam(query, objs);
		query.setCacheable(QueryCache.QUERY_CACHE_TRUE);
		return query.list();
	}
	@Override
	public List<T> executeByHQL(String hql) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(formatSQL(hql));
		query.setCacheable(QueryCache.QUERY_CACHE_TRUE);
		return query.list();
	}
	@Override
	public int getCountByHql(String hql, Object... objs) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(formatSQL(hql));
		query.setCacheable(QueryCache.QUERY_CACHE_TRUE);
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
}
