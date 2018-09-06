package com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model;

import java.util.ArrayList;


public class DBClassStructureInfo {
	private String className;
	private String tableName;
	private String classAttriStr;
	private String nameSpace;
	private ArrayList<DBClassFieldInfo> fieldList;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public ArrayList<DBClassFieldInfo> getFieldList() {
		return fieldList;
	}
	public void setFieldList(ArrayList<DBClassFieldInfo> fieldList) {
		this.fieldList = fieldList;
	}
	public String getClassAttriStr() {
		return classAttriStr;
	}
	public void setClassAttriStr(String classAttriStr) {
		this.classAttriStr = classAttriStr;
	}
	
	
}
