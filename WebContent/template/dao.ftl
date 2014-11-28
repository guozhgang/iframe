package com.skss.app.test;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "${table}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${className} extends IdEntity{
	<#list columns as column>
	private ${column.type} ${column.columnName};
	</#list>
	<#list columns as column>
	@Column(name = "${column.columnName}")
	public ${column.type} get${column.methodName}() {
		return ${column.columnName};
	}
	public void set${column.methodName}(String ${column.columnName}) {
		this.${column.columnName} = ${column.columnName};
	}
	</#list>
	
}
