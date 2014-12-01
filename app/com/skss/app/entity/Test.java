package com.skss.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Test extends IdEntity{
	
	private String testName;
	@Column(name = "test_name")
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
}
