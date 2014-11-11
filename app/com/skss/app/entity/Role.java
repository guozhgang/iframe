package com.skss.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "SS_ROLE")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends IdEntity {
	private String roleName;
	private List<Menu> menuList;
	private String key;  //查找关键字,非持久化变量
	@Column(name = "role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@ManyToMany
	@JoinTable(name = "SS_ROLE_MENU", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	@Transient  
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

}
