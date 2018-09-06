package com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model;

public class SqliteTableInfo {
	private String tabName;
	private String tabSql;
	private String[] fieldSqls;
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public String getTabSql() {
		return tabSql;
	}
	public void setTabSql(String tabSql) {
		this.tabSql = tabSql;
	}
	public String[] getFieldSqls() {
		return fieldSqls;
	}
	public void setFieldSqls(String[] fieldSqls) {
		this.fieldSqls = fieldSqls;
	}
	
	

}
