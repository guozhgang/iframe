package com.skss.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "SS_ORG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Organization extends IdEntity{
	private String text;
	private String code;
	private String parent;
	private String key;
	private User user;
	@Column(name = "name")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Transient
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Column(name = "parent")
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	
	

}
