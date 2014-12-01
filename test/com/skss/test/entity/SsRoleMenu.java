package com.skss.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "ss_role_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SsRoleMenu extends IdEntity{
	private String roleId;
	private String menuId;
	@Column(name = "roleId")
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name = "menuId")
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
