package com.skss.iframe.entity;

public class Template {
	public Template() {}
	public Template(String columnName, String methodName, String type) {
		this.columnName = columnName;
		this.methodName = methodName;
		this.type = type;
	}
	private String columnName;
	private String methodName;
	private String type;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
