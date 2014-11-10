package com.skss.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "SS_ROLE")
public class Role extends IdEntity {
	private String roleName;
	private List<Menu> menuList;

	@Column(name = "role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SS_ROLE_MENU", joinColumns = { @JoinColumn(name = "role_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "menu_id", nullable = false) })
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

}
