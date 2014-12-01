package com.skss.iframe.entity;

public class Template {
	public Template() {
	}

	public Template(String columnName, String tableColumn, String methodName,
			String type) {
		this.columnName = columnName;
		this.tableColumn = tableColumn;
		this.methodName = methodName;
		this.type = type;
	}

	private String columnName;
	private String tableColumn;
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

	public String getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(String tableColumn) {
		this.tableColumn = tableColumn;
	}

}
