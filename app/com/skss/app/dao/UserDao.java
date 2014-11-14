package com.skss.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.skss.app.entity.User;
import com.skss.iframe.dao.BaseDao;
@Repository
public class UserDao extends BaseDao<User>{
	public void saveUser(User user) {
		this.saveOrUpdate(user);
	}
	public List<User> pagelist(int start, int limit) {
		return findPaginationListByHQL("from User", start, limit);
	}
	
	public User login(User user) {
		List<User> list = findListByHQL("from User e where e.loginName = ? and e.password = ?", user.getLoginName(), user.getPassword());
		return list.size() == 0 ? null : list.get(0);
	}
}
