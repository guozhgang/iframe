package com.skss.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.skss.app.dao.OrgDao;
import com.skss.app.dao.OrgTree;
import com.skss.app.entity.Organization;
import com.skss.iframe.entity.TreeNode;


@Service
public class OrgService {
	@Resource
	private OrgDao orgDao;
	
	public void save(Organization org) {
		if (null == org.getId() || "".equals(org.getId())) {
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
	public List<Organization> findOrgById(String id) {
		StringBuilder hql = new StringBuilder("from Organization where id = '" + id + "'");
		return this.orgDao.findListByHQL(hql.toString());
	}
	public int count(String parent) {
		StringBuilder hql = new StringBuilder("select count(i) from Organization i where 1 = 1 ");
		if (!"".equals(parent)) {
			hql.append("and parent = '"+parent+"'");
		}
		return this.orgDao.getCountByHql(hql.toString());
	}
	public List<Organization> list(int start, int limit, String id) {
		String hql = "from Organization where id in ('"+id+"','"+getIds(id)+"')";
		List<Organization> list = this.orgDao.findPaginationListByHQL(hql, start, limit);
		return list;
	}
	/**
	 * 使用递归获取指定节点的所有子节点id
	 * @param id
	 * @return
	 */
	private String getIds(String id) {
		String ids =  "";
		String hql = "from Organization where parent in ('"+id+"')";
		List<Organization> list = this.orgDao.findListByHQL(hql);
		if (list.size() > 0) {
			for (Organization org : list) {
				ids += org.getId() + "','";
			}
			return ids + getIds(ids.substring(0, ids.length() - 3));
		} else 
			return ids;
	}
	/**
	 * 使用递归加载所有的菜单
	 * @param id
	 * @return
	 */
	public List<TreeNode> appendChildren(String id) {
		List<Organization> list = this.list(id);
		List<TreeNode> treelist = new ArrayList<TreeNode>();
		if (list.size() != 0) {
			OrgTree node = null;
			Organization org = null;
			for (int i = 0; i < list.size(); i++) {
				org = list.get(i);
				node = new OrgTree();
				node.setId(org.getId());
				node.setText(org.getText());
				node.setCode(org.getCode());
				if (this.count(org.getId()) == 0) {
					node.setState("open");
				} else {
					node.setState("closed");
					node.setChildren(appendChildren(org.getId()));
				}
				treelist.add(node);
			}
		}
		return treelist;
	}
}

