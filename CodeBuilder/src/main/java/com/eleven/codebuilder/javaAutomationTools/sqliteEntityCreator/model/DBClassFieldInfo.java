package com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model;

public class DBClassFieldInfo {
	private String fieldType;
	private String fieldName;
	private String fieldAttriStr;
	private String className;
	
	private String dbFieldName;
	private String dbFieldType;
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDbFieldName() {
		return dbFieldName;
	}
	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}
	public String getDbFieldType() {
		return dbFieldType;
	}
	public void setDbFieldType(String dbFieldType) {
		this.dbFieldType = dbFieldType;
	}
	public String getFieldAttriStr() {
		return fieldAttriStr;
	}
	public void setFieldAttriStr(String fieldAttriStr) {
		this.fieldAttriStr = fieldAttriStr;
	}
	
	

}
