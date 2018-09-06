//package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate;
//
//import com.eleven.common.StringHelper;
//import com.eleven.javaAutomationTools.codeCreator.model.ClassFieldInfo;
//import com.eleven.javaAutomationTools.codeCreator.model.ClassStructureInfo;
//
//public class JavaTemplate extends IScriptTemplate{
//
//	@Override
//	public String getScript(ClassStructureInfo... clsStructs) {
//		ClassStructureInfo clsStruct = clsStructs[0];
//		StringBuilder sb = new StringBuilder();
//
//appendLine(sb,"package " + clsStruct.getNameSpace() + ";");
//line(sb);
//appendLine(sb,"import java.util.ArrayList;");
//appendLine(sb,"import java.util.Date;");
//line(sb);
//appendLine(sb,"public class " + StringHelper.upperCaseFirst(clsStruct.getClassName()) + " {");
//line(sb);
//for(ClassFieldInfo item : clsStruct.getFieldList()){
//
////	appendLine(sb,"	@SerializedName(\""+item.getFieldName()+"\")");
////	appendLine(sb,"	private " + getScriptType(item) + " " + StringHelper.lowerCaseFirst(item.getFieldName()) + ";");
//	appendLine(sb,"	private " + getScriptType(item) + " " + item.getFieldName() + ";");
//}
//line(sb);
//for(ClassFieldInfo item : clsStruct.getFieldList()){
////	String filedType = item.getFieldType();
////	if(item.isArray()){
////		filedType = "ArrayList<" + filedType + ">";
////	}
//
//	appendLine(sb,"	public void set" + StringHelper.upperCaseFirst(item.getFieldName()) + "("+ getScriptType(item) + " " + StringHelper.lowerCaseFirst(item.getFieldName()) +  "){");
//	appendLine(sb,"		this."+item.getFieldName() + " = " + StringHelper.lowerCaseFirst(item.getFieldName()) + ";" );
////	appendLine(sb,"		this."+StringHelper.lowerCaseFirst(item.getFieldName()) + " = " + StringHelper.lowerCaseFirst(item.getFieldName()) + ";" );
//	appendLine(sb,"	}");
//
//	appendLine(sb,"	public "+getScriptType(item)+" get" + StringHelper.upperCaseFirst(item.getFieldName()) + "(){");
//	appendLine(sb,"		return this."+item.getFieldName() + ";" );
////	appendLine(sb,"		return this."+StringHelper.lowerCaseFirst(item.getFieldName()) + ";" );
//	appendLine(sb,"	}");
//}
////appendLine(sb,"");
//line(sb);
//appendLine(sb,"}");
//
//
//		return sb.toString();
//	}
//
//	@Override
//	public String getFileExName() {
//		return "java";
//	}
//
////	private String getScriptType(ClassFieldInfo item){
////		String filedType = item.getFieldType();
////		if(item.isArray()){
////			if(false){
////				//nothing
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.BOOLEAN)){
////				filedType = "ArrayList<Boolean>";
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.DOUBLE)){
////				filedType = "ArrayList<Double>";
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.FLOAT)){
////				filedType = "ArrayList<Float>";
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.INT)){
////				filedType = "ArrayList<Integer>";
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.LONG)){
////				filedType = "ArrayList<Long>";
////			}else{
////				filedType = "ArrayList<"+filedType+">";
////			}
////
////		}
////		return filedType;
////	}
//
//}
