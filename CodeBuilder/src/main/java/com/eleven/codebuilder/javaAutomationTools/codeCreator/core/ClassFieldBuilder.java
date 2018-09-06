package com.eleven.codebuilder.javaAutomationTools.codeCreator.core;


import com.eleven.codebuilder.common.PrintHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.statics.ClassFieldBaseTypeEnum;

public class ClassFieldBuilder {
	private static String getFieldType(String baseFileTypeEnum){
		return baseFileTypeEnum;
	}
	
	public static ClassFieldInfo get(Object val, String className, String fieldname){
		ClassFieldInfo fieldInfo = new ClassFieldInfo();
		fieldInfo.setFieldName(fieldname);
		fieldInfo.setClassName(className);
		
		if(false){
			//nothing
		}else if(val instanceof Double){
			 PrintHelper.print(val+" ..... Double " + fieldname);
			 fieldInfo.setFieldType(getFieldType(ClassFieldBaseTypeEnum.DOUBLE));
		}else if (val instanceof Integer) {
			 PrintHelper.print(val+" ..... Integer " + fieldname);
			 fieldInfo.setFieldType(getFieldType(ClassFieldBaseTypeEnum.INT));
		}else if(val instanceof Boolean){
			PrintHelper.print(val+" ..... Boolean " + fieldname);
			fieldInfo.setFieldType(getFieldType(ClassFieldBaseTypeEnum.BOOLEAN));
		}else if(val instanceof String){
			 PrintHelper.print(val+" ..... String " + fieldname);
			 if(((String) val).trim().equals("")){
				 fieldInfo.setFieldType(getFieldType(ClassFieldBaseTypeEnum.STRING));
			 }else if(((String) val).trim().equals("this")){
				 fieldInfo.setFieldType(className);
			 }else if(((String) val).trim().equals("this[]")){
				 fieldInfo.setFieldType("ArrayList<"+className+">");
			 }
			 else{
				 fieldInfo.setFieldType(((String) val).trim());
			 }
			 
		}else{
			 PrintHelper.print(val+" ..... String " + fieldname);
			 fieldInfo.setFieldType(getFieldType(ClassFieldBaseTypeEnum.STRING));
		}
		
		return fieldInfo;
	}

}
