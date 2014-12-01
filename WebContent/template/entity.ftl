package ${entityPackage};

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "${table}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${className} extends IdEntity{
	<#list columns as column>
	<#if column.columnName != "id">private ${column.type} ${column.columnName};</#if>
	</#list>
	<#list columns as column><#if column.columnName != "id">
	@Column(name = "${column.tableColumn}")
	public ${column.type} get${column.methodName}() {
		return ${column.columnName};
	}
	public void set${column.methodName}(String ${column.columnName}) {
		this.${column.columnName} = ${column.columnName};
	}
	</#if></#list>
	
}
