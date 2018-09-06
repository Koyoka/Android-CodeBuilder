package com.eleven.codebuilder.javaAutomationTools.codeCreator.model;

import com.eleven.codebuilder.common.StringHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.statics.ClassFieldBaseTypeEnum;

public class ClassFieldInfo {
	

	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getUpFirstFieldName() {
		return StringHelper.upperCaseFirst(fieldName);
	}
	public String getLowFirstFieldName() {
		return StringHelper.lowerCaseFirst(fieldName);
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	private String fieldType;
	private String fieldName;
	private String className;
	private boolean isArray;
	public boolean isArray() {
		return isArray;
	}
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getScriptType(){
		ClassFieldInfo item = this;
		String filedType = item.getFieldType();
		if(item.isArray()){
			if(false){
				//nothing
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.BOOLEAN)){
				filedType = "ArrayList<Boolean>";
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.DOUBLE)){
				filedType = "ArrayList<Double>";
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.FLOAT)){
				filedType = "ArrayList<Float>";
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.INT)){
				filedType = "ArrayList<Integer>";
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.LONG)){
				filedType = "ArrayList<Long>";
			}else{
				filedType = "ArrayList<"+filedType+">";
			}

		}
		return filedType;
	}
}
