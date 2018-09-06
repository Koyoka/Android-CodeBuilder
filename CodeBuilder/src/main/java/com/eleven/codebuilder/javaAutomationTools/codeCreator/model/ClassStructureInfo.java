package com.eleven.codebuilder.javaAutomationTools.codeCreator.model;

import com.eleven.codebuilder.common.StringHelper;

import java.util.ArrayList;

public class ClassStructureInfo {
	
	private String className;
	public String getClassName() {
		return className;
	}
	public String getUpFirstClassName() {
		return StringHelper.upperCaseFirst(className);
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public ArrayList<ClassFieldInfo> getFieldList() {
		return fieldList;
	}
	public void setFieldList(ArrayList<ClassFieldInfo> fieldList) {
		this.fieldList = fieldList;
	}
	private String nameSpace;
	private ArrayList<ClassFieldInfo> fieldList;
	
	
	

}
