package com.skss.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "SS_ORG")
public class Organization extends IdEntity{
	private String orgName;
	private String orgCode;
	
	@Column(name = "name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name = "code")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
